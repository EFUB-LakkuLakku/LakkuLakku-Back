package com.efub.lakkulakku.domain.users.service;

import com.efub.lakkulakku.domain.users.entity.SocialLoginType;

public interface SocialOauth {

	String getOauthRedirectURL();

	String requestAccessToken(String code);

	default SocialLoginType type() {
		if (this instanceof GoogleOauth) {
			return SocialLoginType.GOOGLE;
		} else {
			return null;
		}
	}
}
