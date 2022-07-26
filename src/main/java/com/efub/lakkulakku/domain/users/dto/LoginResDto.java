package com.efub.lakkulakku.domain.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResDto {

	private String message;
	private Object data;  // TODO: token

	@Builder
	public LoginResDto(String message, Object data) {
		this.message = message;
		this.data = data;
	}
}
