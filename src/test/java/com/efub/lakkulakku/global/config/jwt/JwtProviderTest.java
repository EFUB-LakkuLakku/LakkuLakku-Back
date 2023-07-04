package com.efub.lakkulakku.global.config.jwt;

import com.efub.lakkulakku.domain.users.service.UsersService;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.PostConstruct;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class JwtProviderTest {

	@Autowired
	private MockMvc mvc;

	@InjectMocks // 실제 객체로 생성되며, @Mock이 붙은 객체를 주입 받습니다.
	JwtProvider jwtProvider;

	private final String userId = "test123";
	private final String roles = "USER";
	private final Long tokenInvalidTime = 1000L * 60 * 120; // 2h

	String SECRET_KEY = "secret-key";
	@PostConstruct
	protected void init() {
		SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
	}

/*	@Test
	public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/test")).andExpect(status().isForbidden());
	}*/


	@Test
	@DisplayName("accessToken생성_성공")
	public void shouldGenerateAccessToken() throws Exception {
		Long tokenInvalidTime = 1000L * 60 * 120; // 2h
		String email = "test1234@gmail.com";
		String password = "test1234!!";
		String accessToken = "test_access_token";
		String refreshToken = "test_refresh_token";

		//final Long TOKEN_VALID_TIME = 1000L * 60 * 120; // 2h


		given(jwtProvider.createToken(email, "USERS", tokenInvalidTime)).willReturn(refreshToken);
		given(jwtProvider.createToken(email, "USERS", tokenInvalidTime)).willReturn(accessToken);

		//then
		String token = jwtProvider.createAccessToken("userId", "USERS");


		assertNotNull(token);
		assertEquals(userId, Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(accessToken).getBody().get("userId"));
		assertEquals(roles, Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(accessToken).getBody().get("roles"));
		//mvc.perform(MockMvcRequestBuilders.get("/test").header("Authorization", token)).andExpect(status().isOk());
	}



	@Test
	void createAccessToken() {
	}

	@Test
	void createRefreshToken() {
	}
}