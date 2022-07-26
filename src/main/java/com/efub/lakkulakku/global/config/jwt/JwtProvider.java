package com.efub.lakkulakku.global.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
	@Value("${spring.jwt.secret-key}")
	private String SECRET_KEY;
	private static final Long TOKEN_VALID_TIME = 1000L * 60 * 3; // 3m

	// 의존성 주입 후, 초기화를 수행
	// 객체 초기화, secretKey Base64로 인코딩한다.
	@PostConstruct
	protected void init() {
		SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
	}

	public String createToken(String userId, String roles){
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
}
