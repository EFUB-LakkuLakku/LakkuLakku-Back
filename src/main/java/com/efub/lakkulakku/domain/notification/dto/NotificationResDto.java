package com.efub.lakkulakku.domain.notification.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationResDto {
	private UUID id;
	private String message;

	@Builder
	public NotificationResDto(UUID id, String message) {
		this.id = id;
		this.message = message;
	}
}
