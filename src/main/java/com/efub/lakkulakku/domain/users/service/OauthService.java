package com.efub.lakkulakku.domain.users.service;

import com.efub.lakkulakku.domain.users.entity.SocialLoginType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OauthService {
	private final List<SocialOauth> socialOauthList;
	private final HttpServletResponse response;

	public void request(SocialLoginType socialLoginType) {
		SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
		String redirectURL = socialOauth.getOauthRedirectURL();
		try {
			response.sendRedirect(redirectURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String requestAccessToken(SocialLoginType socialLoginType, String code) {
		SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
		return socialOauth.requestAccessToken(code);
	}

	private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType) {
		return socialOauthList.stream()
				.filter(x -> x.type() == socialLoginType)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
	}
}
