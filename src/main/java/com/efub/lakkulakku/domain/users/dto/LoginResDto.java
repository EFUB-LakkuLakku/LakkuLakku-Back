package com.efub.lakkulakku.domain.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResDto {
	private String accessToken;
	private String refreshToken;
	private String nickname;

	@Builder
	public LoginResDto(String accessToken, String refreshToken, String nickname) {

		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.nickname = nickname;
	}
}
