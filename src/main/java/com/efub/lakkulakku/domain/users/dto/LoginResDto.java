package com.efub.lakkulakku.domain.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResDto {
	private String accessToken;

	@Builder
	public LoginResDto(String accessToken) {
		this.accessToken = accessToken;
	}
}
