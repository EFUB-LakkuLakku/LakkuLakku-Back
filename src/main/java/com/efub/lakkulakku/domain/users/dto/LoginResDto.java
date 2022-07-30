package com.efub.lakkulakku.domain.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResDto {
	private String accessToken;
	private String refreshToken;

	@Builder
	public LoginResDto(String accessToken, String refreshToken) {

		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
}
