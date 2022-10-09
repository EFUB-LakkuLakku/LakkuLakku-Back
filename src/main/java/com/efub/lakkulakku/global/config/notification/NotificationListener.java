package com.efub.lakkulakku.global.config.notification;

import com.efub.lakkulakku.domain.notification.dto.NotificationReqDto;
import com.efub.lakkulakku.domain.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class NotificationListener {

	private final NotificationService notificationService;

	@TransactionalEventListener
	@Async
	public void handleNotification(NotificationReqDto notificationReqDto){
		notificationService.send(notificationReqDto.getReceiver(), notificationReqDto.getNotiType(),
				notificationReqDto.getMessage());
	}
}
