package com.efub.lakkulakku.domain.users.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MailVo {

	String toAddress;
	String title;
	String message;
	String fromAddress;

	@Builder
	public MailVo(String toAddress, String title, String message, String fromAddress)
	{
		this.toAddress = toAddress;
		this.title = title;
		this.message = message;
		this.fromAddress = fromAddress;
	}
}
