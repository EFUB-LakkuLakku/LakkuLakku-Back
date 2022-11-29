package com.efub.lakkulakku.domain.notification.dto;

import com.efub.lakkulakku.domain.notification.entity.Notification;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationHomeMapper {

	public NotificationHomeResDto toNotificationHomeResDto(Notification entity) {
		if (entity == null)
			return null;

		return NotificationHomeResDto.builder()
				.id(entity.getId())
				.message(entity.getMessage())
				.build();
	}
}
