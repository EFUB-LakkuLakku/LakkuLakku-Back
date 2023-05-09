package com.efub.lakkulakku.domain.users.controller;

import com.efub.lakkulakku.domain.users.dao.CertificationDao;
import com.efub.lakkulakku.domain.users.dto.LoginInfoDto;
import com.efub.lakkulakku.domain.users.dto.LoginReqDto;
import com.efub.lakkulakku.domain.users.dto.LoginResDto;
import com.efub.lakkulakku.domain.users.dto.SignupReqDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.domain.users.service.MailSendService;
import com.efub.lakkulakku.domain.users.service.UsersService;
import com.efub.lakkulakku.global.config.TestUsers;
import com.efub.lakkulakku.global.config.jwt.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.PostConstruct;

import static org.apache.tomcat.util.http.FastHttpDateFormat.formatDate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
	@MockBean
	JwtProvider jwtProvider;

	@Autowired
	private MockMvc mockMvc;

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
						.content(new ObjectMapper().writeValueAsString(reqDto)).content(new ObjectMapper().writeValueAsString(reqDto))
						.with(csrf())); //403 에러로 인해 추가
		//then
		perform
				.andExpect(status().isCreated());//자원 생성이므로 변경

	}


	@Test
	@TestUsers
	@DisplayName("로그인_성공")
	void login() throws Exception {
		//given
		String email = "test1234@gmail.com";
		String password = "test1234!!";
		String nickname = "test12";
		String accessToken = "test_access_token";
		String refreshToken = "test_refresh_token";
		String type = "refreshToken";

		LoginReqDto loginReqDto = LoginReqDto.builder()
				.email(email)
				.password(password)
				.build();
		LoginInfoDto loginInfoDto = LoginInfoDto.builder()
				.nickname(nickname)
				.accessToken(accessToken)
				.refreshToken(refreshToken)
				.build();
		LoginResDto loginResDto = loginInfoDto.toLoginResDto();
		ResponseCookie responseCookie = ResponseCookie.from(type, refreshToken)
				.maxAge(7 * 24 * 60 * 60)
				.path("/")
				.secure(true)
				.sameSite("None")
				.httpOnly(true)
				.build();

		given(usersService.login(email, password)).willReturn(loginInfoDto);
		given(usersService.generateCookie(type, refreshToken)).willReturn(responseCookie);

		// when
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/login")//MvcResult는 ResultActions가 수행한 요청에 대한 정보를 제공하는 객체
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(loginReqDto))
						.with(csrf()))
				.andReturn();

		// then
		mvcResult.getResponse()
				.setCharacterEncoding("UTF-8");

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(new ObjectMapper().writeValueAsString(loginResDto));

		String expectedCookieValue = "refreshToken=" + refreshToken +
				"; Path=/; Max-Age=" + 7 * 24 * 60 * 60 +
				"; Expires=" + formatDate(System.currentTimeMillis() + responseCookie.getMaxAge().toMillis())+ //ResponseCookie 클래스의 maxAge 메서드는 매개변수로 초를 받기 때문에, 만료 시간을 초 단위로 계산해서 전달
				"; Secure; HttpOnly; SameSite=None";

		assertThat(mvcResult.getResponse().getHeader(HttpHeaders.SET_COOKIE)).isEqualTo(expectedCookieValue);
	}


}
