package com.efub.lakkulakku.domain.users.controller;

import com.efub.lakkulakku.domain.users.dao.CertificationDao;
import com.efub.lakkulakku.domain.users.dto.LoginInfoDto;
import com.efub.lakkulakku.domain.users.dto.LoginReqDto;
import com.efub.lakkulakku.domain.users.dto.SignupReqDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.domain.users.service.CustomUsersDetailsService;
import com.efub.lakkulakku.domain.users.service.MailSendService;
import com.efub.lakkulakku.domain.users.service.UsersService;
import com.efub.lakkulakku.global.config.TestUsers;
import com.efub.lakkulakku.global.config.WithUserDetailsSecurityContextFactory;
import com.efub.lakkulakku.global.config.jwt.JwtProvider;
import com.efub.lakkulakku.global.redis.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;


@WebMvcTest({LoginController.class})//웹 계층 테스트를 위한 어노테이션
@ExtendWith(MockitoExtension.class)
@MockBean(JpaMetamodelMappingContext.class)
public class UsersControllerTest {
	@MockBean//스프링 컨텍스트의 빈을 대체하는 목 객체를 만든다. 애플리케이션 컨텍스트에 빈으로 등록되어 다른 빈에서 주입 받을 수 있음
	UsersService usersService;

	@MockBean
	UsersRepository usersRepository;

	@MockBean
	MailSendService mailSendService;
	@MockBean
	CertificationDao certificationDao;


/*	@MockBean
	CustomUsersDetailsService customUsersDetailsService;*/


	@MockBean
	private RedisTemplate<String, String> redisTemplate;


	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;


	@Test
	@WithMockUser
	@DisplayName("회원가입_성공")
	void signUp() throws Exception {
		String email = "test1234@gmail.com";
		String password = "test1234!!";
		String nickname = "test12";

		final SignupReqDto reqDto = SignupReqDto.builder()
				.email(email)
				.password(password)
				.nickname(nickname)
				.build();

		Users users = reqDto.toEntity();

		given(usersService.signup(any(SignupReqDto.class))).willReturn(users);

		//when

		ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/signup")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(reqDto))
						.with(csrf())); //403 에러로 인해 추가
		//then
		perform
				.andExpect(status().isCreated());//자원 생성이므로 변경

		//verify(usersService).signup(reqDto);
	}

/*	@Test
	@TestUsers
	@DisplayName("로그인_성공")
	void login() throws Exception {
		String email = "test1234@gmail.com";
		String password = "test1234!!";
		LoginReqDto loginReqDto = LoginReqDto
				.builder()
				.email(email)
				.password(password)
				.build();
		LoginInfoDto loginInfoDto = LoginInfoDto.builder()
				.accessToken("test_access_token")
				.refreshToken("test_refresh_token")
				.build();

		given(usersService.login(email, password)).willReturn(loginInfoDto);


		ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(loginReqDto))
				.with(csrf()));
		perform.andExpect(status().isCreated())
				.andExpect((ResultMatcher) jsonPath("$.accessToken", is("test_access_token")))
				.andExpect((ResultMatcher) jsonPath("$.refreshToken", is("test_refresh_token")))
				.andExpect(header().string("Set-Cookie", containsString("refreshToken=test_refresh_token;")));

		verify(usersService).login(email, password);

	}*/
}
