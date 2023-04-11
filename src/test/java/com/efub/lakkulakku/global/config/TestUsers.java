package com.efub.lakkulakku.global.config;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithUserDetailsSecurityContextFactory.class)
public @interface TestUsers {
	//@TestMember 애너테이션 값으로 전달된 사용자 정보를 사용하여 인증된 SecurityContext를 생성
	//실제 서비스에서 @AuthUsers 커스텀 어노테이션으로 인증된 사용자의 정보를 가져오는 것과 비슷
	String email() default "test1234@gmail.com";
	String password() default "test1234!!";
	String nickname() default "test";
	String[] roles() default {"USER"};
}
