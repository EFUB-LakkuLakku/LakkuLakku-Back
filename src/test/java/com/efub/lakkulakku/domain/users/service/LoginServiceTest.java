package com.efub.lakkulakku.domain.users.service;

import com.efub.lakkulakku.domain.friend.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.users.dto.LoginInfoDto;
import com.efub.lakkulakku.domain.users.dto.LoginReqDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.exception.PasswordNotMatchedException;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.global.config.jwt.JwtProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

	@InjectMocks // 실제 객체로 생성되며, @Mock이 붙은 객체를 주입 받습니다.
	UsersService usersService;

	@Mock
	UsersRepository usersRepository;

	@Mock
	PasswordEncoder passwordEncoder;

	@Mock
	JwtProvider jwtProvider;

	@Test
	@DisplayName("로그인_성공")
	void login() {
		String email = "test1234@gmail.com";
		String password = "test1234!!";
		String nickname = "test";
		String accessToken = "accessToken";
		String refreshToken = "refreshToken";

		LoginReqDto loginReqDto = LoginReqDto
				.builder()
				.email(email)
				.password(password)
				.build();
		Users users = Users.builder()
				.nickname(nickname)
				.email(email)
				.password(password)
				.build();

		given(usersRepository.findByEmail(loginReqDto.getEmail())).willReturn(Optional.of(users));
		given(jwtProvider.createAccessToken(loginReqDto.getEmail(), users.getRole())).willReturn(accessToken);
		given(jwtProvider.createRefreshToken(loginReqDto.getEmail(), users.getRole())).willReturn(refreshToken);
		given(passwordEncoder.matches(password, password)).willReturn(true);

		//when
		LoginInfoDto result = usersService.login(email, password);

		//then
		assertEquals(accessToken, result.getAccessToken());
		assertEquals(refreshToken, result.getRefreshToken());
		assertEquals(nickname, result.getNickname());
	}

	@Test
	@DisplayName("존재하지 않는 이메일로 로그인 테스트")
	void loginByInvalidEmail() {
		String email = "test1234@gmail.com";
		String wrongEmail = "worng@gmail.com";
		String password = "test1234!!";
		String nickname = "test";
		LoginReqDto loginReqDto = LoginReqDto
				.builder()
				.email(wrongEmail)
				.password(password)
				.build();
		Users users = Users.builder()
				.nickname(nickname)
				.email(email)
				.password(password)
				.build();
		given(usersRepository.findByEmail(loginReqDto.getEmail())).willThrow(new UserNotFoundException());

		assertThrows(UserNotFoundException.class, () -> usersService.login(loginReqDto.getEmail(), loginReqDto.getPassword()));
	}

	@Test
	@DisplayName("비밀번호_불일치_로그인_실패")
	void loginByInvalidPassword() {
		String email = "test1234@gmail.com";
		String password = "test1234!!";
		String wrongPassword = "wrong!!";
		String nickname = "test";
		LoginReqDto loginReqDto = LoginReqDto
				.builder()
				.email(email)
				.password(wrongPassword)
				.build();
		Users users = Users.builder()
				.nickname(nickname)
				.email(email)
				.password(password)
				.build();
		given(usersRepository.findByEmail(loginReqDto.getEmail())).willReturn(Optional.of(users));

		//when
		assertThrows(PasswordNotMatchedException.class, () -> usersService.login(loginReqDto.getEmail(), loginReqDto.getPassword()));
	}




}
