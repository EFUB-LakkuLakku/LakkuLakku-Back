package com.efub.lakkulakku.domain.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GoogleOauth implements SocialOauth {


	private String GOOGLE_SNS_BASE_URL = "https://accounts.google.com/o/oauth2/v2/auth";
	@Value("${spring.security.oauth2.client.registration.google.clientId}")
	private String GOOGLE_SNS_CLIENT_ID;

	private String GOOGLE_SNS_CALLBACK_URL ="http://localhost:8080/auth/google/callback";
	@Value("${spring.security.oauth2.client.registration.google.clientSecret}")
	private String GOOGLE_SNS_CLIENT_SECRET;

	private String GOOGLE_SNS_TOKEN_BASE_URL="https://oauth2.googleapis.com/token";


	@Override
	public String getOauthRedirectURL() {
		Map<String, Object> params = new HashMap<>();
		params.put("scope", "email");
		params.put("response_type", "code");
		params.put("client_id", GOOGLE_SNS_CLIENT_ID);
		params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);

		String parameterString = params.entrySet().stream()
				.map(x -> x.getKey() + "=" + x.getValue())
				.collect(Collectors.joining("&"));

		return GOOGLE_SNS_BASE_URL + "?" + parameterString;
	}

	@Override
	public String requestAccessToken(String code) {
		RestTemplate restTemplate = new RestTemplate();

		Map<String, Object> params = new HashMap<>();
		params.put("code", code);
		params.put("client_id", GOOGLE_SNS_CLIENT_ID);
		params.put("client_secret", GOOGLE_SNS_CLIENT_SECRET);
		params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);
		params.put("grant_type", "authorization_code");

		ResponseEntity<String> responseEntity =
				restTemplate.postForEntity(GOOGLE_SNS_TOKEN_BASE_URL, params, String.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		}
		return "구글 로그인 요청 처리 실패";
	}

	public String requestAccessTokenUsingURL(String code) {
		try {
			URL url = new URL(GOOGLE_SNS_TOKEN_BASE_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setDoOutput(true);

			Map<String, Object> params = new HashMap<>();
			params.put("code", code);
			params.put("client_id", GOOGLE_SNS_CLIENT_ID);
			params.put("client_secret", GOOGLE_SNS_CLIENT_SECRET);
			params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);
			params.put("grant_type", "authorization_code");

			String parameterString = params.entrySet().stream()
					.map(x -> x.getKey() + "=" + x.getValue())
					.collect(Collectors.joining("&"));

			BufferedOutputStream bous = new BufferedOutputStream(conn.getOutputStream());
			bous.write(parameterString.getBytes());
			bous.flush();
			bous.close();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			StringBuilder sb = new StringBuilder();
			String line;

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			if (conn.getResponseCode() == 200) {
				return sb.toString();
			}
			return "구글 로그인 요청 처리 실패";
		} catch (IOException e) {
			throw new IllegalArgumentException("알 수 없는 구글 로그인 Access Token 요청 URL 입니다 :: " + GOOGLE_SNS_TOKEN_BASE_URL);
		}
	}
}
