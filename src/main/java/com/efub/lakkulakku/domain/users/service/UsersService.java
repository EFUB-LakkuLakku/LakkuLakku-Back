package com.efub.lakkulakku.domain.users.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.efub.lakkulakku.domain.diary.dto.DiaryHomeMapper;
import com.efub.lakkulakku.domain.diary.dto.DiaryHomeResDto;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.friend.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.notification.dto.NotificationMapper;
import com.efub.lakkulakku.domain.notification.dto.NotificationResDto;
import com.efub.lakkulakku.domain.notification.entity.Notification;
import com.efub.lakkulakku.domain.notification.repository.NotificationRepository;
import com.efub.lakkulakku.domain.profile.ProfileRepository;
import com.efub.lakkulakku.domain.profile.entity.Profile;
import com.efub.lakkulakku.domain.users.dto.HomeMapper;
import com.efub.lakkulakku.domain.users.dto.HomeResDto;
import com.efub.lakkulakku.domain.users.dto.LoginInfoDto;
import com.efub.lakkulakku.domain.users.dto.LoginReqDto;
import com.efub.lakkulakku.domain.users.dto.NewPwdReqDto;
import com.efub.lakkulakku.domain.users.dto.SignupReqDto;
import com.efub.lakkulakku.domain.users.dto.WithdrawReqDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.exception.PasswordNotMatchedException;
import com.efub.lakkulakku.domain.users.exception.PasswordsNotEqualException;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.global.config.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersService {

	private final UsersRepository usersRepository;
	private final ProfileRepository profileRepository;
	private final NotificationRepository notificationRepository;
	private final DiaryRepository diaryRepository;
	private final DiaryHomeMapper diaryHomeMapper;
	private final HomeMapper homeMapper;

	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;

	public Users signup(SignupReqDto reqDto) {
		reqDto.setPassword(passwordEncoder.encode(reqDto.getPassword()));
		Users user = usersRepository.save(reqDto.toEntity());
		Profile profile = Profile.builder()
			.file(null)
			.bio("반갑습니다 :)")
			.build();
		profileRepository.save(profile);
		return user;
	}

	public Users findUsersByEmail(LoginReqDto loginReqDto) {
		return usersRepository.findByEmail(loginReqDto.getEmail())
			.orElseThrow(UserNotFoundException::new);
	}

	public String getTempString() {
		char[] charSet = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

		StringBuilder tempString = new StringBuilder();

		/* 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 조합 */
		int idx = 0;
		for (int i = 0; i < 10; i++) {
			idx = (int)(charSet.length * Math.random());
			tempString.append(charSet[idx]);
		}

		return tempString.toString();
	}

	public void updatePassword(String tempPwd, Users user) {
		String encryptPassword = passwordEncoder.encode(tempPwd);
		user.setPassword(encryptPassword);
		usersRepository.save(user);
	}

	public LoginInfoDto login(String email, String password) {
		Users user = usersRepository
			.findByEmail(email).orElseThrow(UserNotFoundException::new);
		checkPassword(password, user.getPassword());
		String accessToken = jwtProvider.createAccessToken(user.getEmail(), user.getRole());
		String refreshToken = jwtProvider.createRefreshToken(user.getEmail(), user.getRole());
		return new LoginInfoDto(accessToken, refreshToken, user.getNickname());
	}

	public LoginInfoDto reIssueAccessToken(String email, String refreshToken) {
		Users user = usersRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
		jwtProvider.checkRefreshToken(email, refreshToken);
		String accessToken = jwtProvider.createAccessToken(user.getEmail(), user.getRole());
		return new LoginInfoDto(accessToken, refreshToken, user.getNickname());
	}

	public ResponseCookie generateCookie(String type, String token) {
		ResponseCookie cookie = ResponseCookie.from(type, token)
			.maxAge(7 * 24 * 60 * 60)
			.path("/")
			.secure(true)
			.sameSite("None")
			.httpOnly(true)
			.build();
		return cookie;

	}

	private void checkPassword(String password, String encodedPassword) {
		boolean isSame = passwordEncoder.matches(password, encodedPassword);
		if (!isSame) {
			throw new PasswordNotMatchedException();
		}

	}

	public LoginInfoDto provideToken(String email) {
		Users user = usersRepository
			.findByEmail(email).orElseThrow(UserNotFoundException::new);
		String accessToken = jwtProvider.createAccessToken(user.getEmail(), user.getRole());
		String refreshToken = jwtProvider.createRefreshToken(user.getEmail(), user.getRole());
		return new LoginInfoDto(accessToken, refreshToken, user.getNickname());
	}

	public void newPassword(Users user, NewPwdReqDto reqDto) {
		String newPwd = reqDto.getNewPwd();
		String checkNewPwd = reqDto.getCheckNewPwd();
		if (checkNewPwd.equals(newPwd)) {
			user.setPassword(passwordEncoder.encode(newPwd));
			usersRepository.save(user);
		} else {
			throw new PasswordsNotEqualException();
		}
	}

	public void logout(String email, String accessToken) {
		jwtProvider.logout(email, accessToken);
	}

	public void deleteUser(WithdrawReqDto withdrawReqDto) {
		Users users = usersRepository.findByNickname(withdrawReqDto.getNickname())
			.orElseThrow(UserNotFoundException::new);
		usersRepository.delete(users);
	}

	public HomeResDto getHome(Users user, String year, String month) {
		if (year == null) {
			Date date = new Date();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int nowYear = localDate.getYear();
			int nowMonth = localDate.getMonthValue();

			return homeMapper.toHomeResDto(user, Integer.toString(nowYear), Integer.toString(nowMonth));
		} else {
			return homeMapper.toHomeResDto(user, year, month);
		}
	}

	public List<DiaryHomeResDto> getHomeDiary(Users user, String year, String month) {
		if (year == null) {
			Date date = new Date();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int nowYear = localDate.getYear();
			int nowMonth = localDate.getMonthValue();

			return diaryRepository.findUsersDiaryByYearAndMonth(user.getUserId(), Integer.toString(nowYear),
					Integer.toString(nowMonth))
				.stream()
				.map(diaryHomeMapper::toDiaryHomeResDto)
				.collect(Collectors.toList());
		} else {
			return diaryRepository.findUsersDiaryByYearAndMonth(user.getUserId(), year, month)
				.stream()
				.map(diaryHomeMapper::toDiaryHomeResDto)
				.collect(Collectors.toList());
		}
	}

	@Transactional
	public List<NotificationResDto> findAllNotifications(Users user) {
		List<Notification> notificationList = notificationRepository.findByUsersId(user.getUserId());
		return notificationList.stream()
			.map(NotificationMapper::toNotificationResDto)
			.collect(Collectors.toList());
	}
	@Transactional(readOnly = true)
	public Users findByUid(Long uid){
		return usersRepository.findByUid(uid).orElseThrow(UserNotFoundException::new);
	}

	@Transactional(readOnly = true)
	public Users findByNickname(String nickname) {
		return usersRepository.findByNickname(nickname)
			.orElseThrow(UserNotFoundException::new);
	}

	@Transactional(readOnly = true)
	public Users findUserByUid(Long userId) {
		return usersRepository.findByUid(userId)
			.orElseThrow(UserNotFoundException::new);
	}

}