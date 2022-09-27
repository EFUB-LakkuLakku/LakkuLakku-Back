package com.efub.lakkulakku.domain.users.service;

import com.efub.lakkulakku.domain.friend.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.profile.ProfileRepository;
import com.efub.lakkulakku.domain.profile.entity.Profile;
import com.efub.lakkulakku.domain.users.dto.WithdrawReqDto;
import com.efub.lakkulakku.domain.users.dto.*;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.exception.PasswordNotMatchedException;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.global.config.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UsersService {

	private final UsersRepository usersRepository;
	private final ProfileRepository profileRepository;
	private final HomeMapper homeMapper;

	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;

	@Transactional
	public Users signup(SignupReqDto reqDto) {
		reqDto.setPassword(passwordEncoder.encode(reqDto.getPassword()));
		Users user = usersRepository.save(reqDto.toEntity());
		Profile profile = Profile.builder()
				.file(null)
				.users(user)
				.bio("반갑습니다 :)")
				.build();
		profileRepository.save(profile);
		return user;
	}

	@Transactional
	public Users findUsersByEmail(LoginReqDto loginReqDto) {
		return usersRepository.findByEmail(loginReqDto.getEmail())
				.orElseThrow(UserNotFoundException::new);
	}

	public String getTempPwd() {
		char[] charSet = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
				'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

		String pwd = "";

		/* 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 조합 */
		int idx = 0;
		for(int i = 0; i < 10; i++){
			idx = (int) (charSet.length * Math.random());
			pwd += charSet[idx];
		}

		return pwd;
	}

	//임시 비밀번호로 업데이트
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

	public ResponseCookie generateCookie(String type, String token)
	{
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


	public void logout(String email, String accessToken) {
		jwtProvider.logout(email, accessToken);
	}

	@Transactional
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

}