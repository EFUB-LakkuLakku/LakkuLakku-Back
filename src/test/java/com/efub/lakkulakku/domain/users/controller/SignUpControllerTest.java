package com.efub.lakkulakku.domain.users.controller;

import com.efub.lakkulakku.domain.users.dto.SignupReqDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.service.UsersService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class) //Junit4의 Runwith과 같은 기능을 하는 Junit5 어노테이션
class SignUpControllerTest {//단위 테스트

	@InjectMocks
	LoginController loginController;

	@Mock
	UsersService usersService;

	@Test
	@DisplayName("회원가입_서비스_성공")
	void signupController()  {
		String email = "test1234@gmail.com";
		String password = "test1234!!";
		String nickname = "test";
		String bio = "반갑습니다 :)";
		final SignupReqDto reqDto = SignupReqDto.builder()
				.email(email)
				.password(password)
				.nickname(nickname)
				.build();

		Users users = reqDto.toEntity();

		given(usersService.signup(any(SignupReqDto.class))).willReturn(users);

		// when
		ResponseEntity<String> response = loginController.signup(reqDto);

		// then
		verify(usersService).signup(reqDto);//usersService.signup() 메소드가 호출되었는지 확인
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("성공적으로 가입되었습니다.", response.getBody());

	}






}