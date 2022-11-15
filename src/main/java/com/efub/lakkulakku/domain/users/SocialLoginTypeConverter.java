package com.efub.lakkulakku.domain.users;

import com.efub.lakkulakku.domain.users.entity.SocialLoginType;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class SocialLoginTypeConverter implements Converter<String, SocialLoginType> {
	@Override
	public SocialLoginType convert(String s) {
		return SocialLoginType.valueOf(s.toUpperCase());
	}
}