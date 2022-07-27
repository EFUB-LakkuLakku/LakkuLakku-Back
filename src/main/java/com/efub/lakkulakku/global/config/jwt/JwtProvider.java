package com.efub.lakkulakku.global.config.jwt;

import com.efub.lakkulakku.domain.users.service.CustomUsersDetailsService;
import io.jsonwebtoken.*;
import com.efub.lakkulakku.domain.users.exception.TokenExpiredException;
import com.efub.lakkulakku.global.redis.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

	private final CustomUsersDetailsService customUsersDetailsService;
	@Value("${spring.jwt.secret-key}")
	private String SECRET_KEY;
	private static final Long TOKEN_VALID_TIME = 1000L * 60 * 3; // 3m

	private final RedisService redisService;

	// 의존성 주입 후, 초기화를 수행
	// 객체 초기화, secretKey Base64로 인코딩한다.
	@PostConstruct
	protected void init() {
		SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
	}

	public String createAccessToken(String userId, String roles) {
		Long tokenInvalidTime = 1000L * 60 * 3; // 3m
		return this.createToken(userId, roles, tokenInvalidTime);
	}

	public String createRefreshToken(String userId, String roles) {
		Long tokenInvalidTime = 1000L * 60 * 60 * 24; // 1d
		String refreshToken = this.createToken(userId, roles, tokenInvalidTime);
		redisService.setValues(userId, refreshToken, Duration.ofMillis(tokenInvalidTime));
		return refreshToken;
	}

	public String createToken(String userId, String roles, Long tokenInvalidTime) {
		Claims claims = Jwts.claims().setSubject(userId); // claims 생성 및 payload 설정
		claims.put("roles", roles); // 권한 설정, key/ value 쌍으로 저장
		Date date = new Date();
		return Jwts.builder()
				.setClaims(claims) // 발행 유저 정보 저장
				.setIssuedAt(date) // 발행 시간 저장
				.setExpiration(new Date(date.getTime() + TOKEN_VALID_TIME)) // 토큰 유효 시간 저장
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 해싱 알고리즘 및 키 설정
				.compact(); // 생성
	}

	public Authentication validateToken(HttpServletRequest request, String token) {
		String exception = "exception";
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return getAuthentication(token);
		} catch (MalformedJwtException | SignatureException | UnsupportedJwtException e) {
			request.setAttribute(exception, "토큰의 형식을 확인하세요.");
		} catch (ExpiredJwtException e) {
			request.setAttribute(exception, "토큰이 만료되었습니다.");
		} catch (IllegalArgumentException e) {
			request.setAttribute(exception, "JWT compact of handler are invalid");
		} return null;
	}

	private Authentication getAuthentication(String token) {
		UserDetails userDetails = customUsersDetailsService.loadUserByUsername(getUserEmail(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	private String getUserEmail(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}

	public void checkRefreshToken(String userId, String refreshToken) {
		String redisRT = redisService.getValues(userId);
		if (!refreshToken.equals(redisRT)) {
			throw new TokenExpiredException();
		}
	}
}
