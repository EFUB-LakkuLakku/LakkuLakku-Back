package com.efub.lakkulakku.domain.notification.dto;

import com.efub.lakkulakku.domain.notification.entity.NotificationEnum;
import com.efub.lakkulakku.domain.users.entity.Users;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NotificationReqDto {
	private Users receiver;
	private NotificationEnum notiType;
	private String message;

	@Builder
	public NotificationReqDto(Users receiver, NotificationEnum notiType, String message) {
		this.receiver = receiver;
		this.notiType = notiType;
		this.message = message;
	}
}