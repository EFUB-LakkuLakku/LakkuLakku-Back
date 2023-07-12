package com.efub.lakkulakku.domain.user.controller;

import com.efub.lakkulakku.domain.users.dto.SignupReqDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;


import static com.efub.lakkulakku.global.constant.ResponseConstant.SIGNUP_SUCCESS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@AfterEach
	public void tearDown() throws Exception {
		usersRepository.deleteAll();
	}

	private static final String LOCALHOST_URL = "http://localhost:";
	private static final String BASE_URL = "/api/v1/users";

	@Nested
	@DisplayName("회원가입 테스트")
	class testSignup {

		@Test
		@DisplayName("POST-회원가입 성공")
		public void signup() throws Exception {


			// given
			String email = "signuptest@gmail.com";
			String nickname = "회원가입테스트";
			String password = "1234";
			SignupReqDto signupReqDto = SignupReqDto.builder()
					.email(email)
					.nickname(nickname)
					.password(passwordEncoder.encode(password))
					.build();

			HttpEntity<SignupReqDto> requestEntity = new HttpEntity<>(signupReqDto);
			String url = LOCALHOST_URL + port + BASE_URL + "/signup";

			// when
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

			// then
			assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(responseEntity.getBody()).isEqualTo(SIGNUP_SUCCESS);

			Users user = usersRepository.findByEmail(signupReqDto.getEmail()).orElseThrow(UserNotFoundException::new);
			assertThat(user.getEmail()).isEqualTo(signupReqDto.getEmail());
			assertThat(user.getNickname()).isEqualTo(signupReqDto.getNickname());
		}
	}
}
