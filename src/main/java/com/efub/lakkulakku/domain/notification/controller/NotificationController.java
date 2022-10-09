package com.efub.lakkulakku.domain.notification.controller;

import com.efub.lakkulakku.domain.notification.dto.NotificationResDto;
import com.efub.lakkulakku.domain.notification.service.NotificationService;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.service.AuthUsers;
import com.efub.lakkulakku.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {

	private final NotificationService notificationService;
	private final UsersService usersService;

	@GetMapping(value = "/subscribe", produces = "text/event-stream")
	@ResponseStatus(HttpStatus.OK)
	public SseEmitter subscribe(@AuthUsers Users user,
								@RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
		return notificationService.subscribe(user.getId(), lastEventId);
	}

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public List<NotificationResDto> findAllNotifications(@AuthUsers Users user) {
		return usersService.findAllNotifications(user);
	}
}
