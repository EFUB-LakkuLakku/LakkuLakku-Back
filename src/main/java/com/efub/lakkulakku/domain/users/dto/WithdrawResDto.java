package com.efub.lakkulakku.domain.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WithdrawResDto {

	private String nickname;
	private String message;

	@Builder
	public WithdrawResDto(String nickname, String message) {
		this.nickname = nickname;
		this.message = message;
	}
}
