package com.efub.lakkulakku.domain.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginInfoDto {
	private String accessToken;
	private String refreshToken;
	private String nickname;

	@Builder
	public LoginInfoDto(String accessToken, String refreshToken, String nickname) {

		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.nickname = nickname;
	}

	public LoginResDto toLoginResDto() {
		return LoginResDto.builder()
				.nickname(this.nickname)
				.accessToken(this.accessToken)
				.build();
	}
}
