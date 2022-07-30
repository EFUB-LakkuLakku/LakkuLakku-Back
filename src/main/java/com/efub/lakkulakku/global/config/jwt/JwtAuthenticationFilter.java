package com.efub.lakkulakku.global.config.jwt;

import com.efub.lakkulakku.global.config.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtProvider jwtProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token = resolveToken(request.getHeader("Authorization"));
		if (token != null){ // token 검증
			Authentication authentication = jwtProvider.validateToken(request, token);
			SecurityContextHolder.getContext().setAuthentication(authentication); // 사용자 정보를 포함하는 authentication 객체
		}
		filterChain.doFilter(request, response);
	}

	private String resolveToken(String authorization) {
		return authorization != null ? authorization.substring(7) : null;
	}
}
