package com.efub.lakkulakku.domain.notification.entity;

import java.time.LocalDateTime;

import com.efub.lakkulakku.domain.users.entity.Users;

public interface NotificationMessage {
	public static final String friendMessage = "님과 새 친구가 되었습니다.";
	public static final String commentMessage = " 일기에 댓글을 달았습니다.";
	public static final String likeMessage = " 일기에 좋아요를 눌렀습니다.";
	public static final String recoommentMessage = " 일기에 대댓글을 달았습니다.";

	public String getMessage(Users friendId, LocalDateTime date);
}
