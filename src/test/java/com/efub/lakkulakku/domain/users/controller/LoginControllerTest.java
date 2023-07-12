package com.efub.lakkulakku.domain.users.controller;

import com.efub.lakkulakku.domain.users.dto.LoginInfoDto;
import com.efub.lakkulakku.domain.users.dto.LoginReqDto;
import com.efub.lakkulakku.domain.users.dto.LoginResDto;
import com.efub.lakkulakku.domain.users.dto.SignupReqDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.service.UsersService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class LoginControllerTest {

	@InjectMocks
	LoginController loginController;

	@Mock
	UsersService usersService;

	@Test
	@DisplayName("로그인_서비스_성공")
	void loginUsersService(){
		String email = "test1234@gmail.com";
		String password = "test1234!!";
		String nickname = "test";

		String accessToken = "accessToken";
		String refreshToken = "refreshToken";
		final LoginReqDto reqDto = LoginReqDto.builder()
				.email(email)
				.password(password)
				.build();

		final LoginInfoDto loginInfoDto = LoginInfoDto.builder()
				.refreshToken(refreshToken)
				.accessToken(accessToken)
				.nickname(nickname)
				.build();
		LoginResDto loginResDto = loginInfoDto.toLoginResDto();
		given(usersService.login(email, password)).willReturn(loginInfoDto);


		// when
		ResponseEntity<LoginResDto> response = loginController.login(reqDto);

		// then
		verify(usersService).login(email, password);//usersService.signup() 메소드가 호출되었는지 확인
		assertEquals(HttpStatus.OK, response.getStatusCode());
		//assertEquals("성공적으로 가입되었습니다.", response.getBody());

	}

	}
