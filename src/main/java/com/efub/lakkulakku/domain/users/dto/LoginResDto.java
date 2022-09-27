package com.efub.lakkulakku.domain.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResDto {
	private String nickname;

	@Builder
	public LoginResDto(String nickname)
	{
		this.nickname = nickname;
	}
}
