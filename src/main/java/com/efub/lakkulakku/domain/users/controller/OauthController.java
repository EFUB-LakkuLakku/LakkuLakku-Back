package com.efub.lakkulakku.domain.users.controller;

import com.efub.lakkulakku.domain.users.entity.SocialLoginType;
import com.efub.lakkulakku.domain.users.service.OauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@Slf4j
public class OauthController {
	private final OauthService oauthService;

	@GetMapping(value = "/{socialLoginType}")
	public void socialLoginType(
			@PathVariable(name = "socialLoginType") SocialLoginType socialLoginType) {
		log.info(">> 사용자로부터 SNS 로그인 요청을 받음 :: {} Social Login", socialLoginType);
		oauthService.request(socialLoginType);
	}

	@GetMapping(value = "/{socialLoginType}/callback")
	public String callback(
			@PathVariable(name = "socialLoginType") SocialLoginType socialLoginType,
			@RequestParam(name = "code") String code) {
		log.info(">> 소셜 로그인 API 서버로부터 받은 code :: {}", code);
		return oauthService.requestAccessToken(socialLoginType, code);
	}

}