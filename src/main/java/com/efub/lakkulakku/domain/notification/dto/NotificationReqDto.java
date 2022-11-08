package com.efub.lakkulakku.domain.notification.dto;

import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NotificationReqDto {
	private Users receiver;
	private String notiType;
	private String message;

	@Builder
	public NotificationReqDto(Users receiver, String notiType, String message) {
		this.receiver = receiver;
		this.notiType = notiType;
		this.message = message;
	}
}
