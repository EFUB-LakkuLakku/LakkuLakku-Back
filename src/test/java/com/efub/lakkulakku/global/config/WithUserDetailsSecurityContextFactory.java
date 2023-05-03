package com.efub.lakkulakku.global.config;

import com.efub.lakkulakku.domain.users.entity.AuthUsers;
import com.efub.lakkulakku.domain.users.entity.Users;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithUserDetailsSecurityContextFactory implements WithSecurityContextFactory<TestUsers> {
	//@WithSecurityContext 애노테이션으로 마킹된 테스트 메소드에서 Spring Security 인증에 필요한 SecurityContext를 생성하기 위한 메소드를 제공
	//WithUserDetailsSecurityContextFactory 라는 팩토리 클래스를 사용하여 인증된 SecurityContext를 생성

	@Override
	public SecurityContext createSecurityContext(TestUsers annotation) {
		String email = "test1234@gmail.com";
		String password = "test1234!!";
		String nickname = "test";
		Users users = Users.builder()
						.email(email)
						.password(password)
						.nickname(nickname)
						.build();
		UserDetails userAccount = new AuthUsers(users);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userAccount, "", userAccount.getAuthorities());
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(token);
		return context;
	}
}
