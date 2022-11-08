package com.efub.lakkulakku.domain.notification.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotificationMessage {

	private static String MESSAGE = "님이 ";
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static String makeFriendNotification(String nickname) {
		String FRIEND_MESSAGE = "님과 친구가 되었습니다.";
		return nickname + FRIEND_MESSAGE;
	}

	public static String makeCommentNotification(String nickname, LocalDateTime date) {
		String DIARY_COMMENT_MESSAGE = " 일기에 댓글을 달았습니다.";
		String formattedDate = date.format(dateTimeFormatter);
		return nickname + MESSAGE + formattedDate + DIARY_COMMENT_MESSAGE;
	}

	public static String makeLikeNotification(String nickname, LocalDateTime date) {
		String DIARY_LIKE_MESSAGE = " 일기를 좋아합니다.";
		String formattedDate = date.format(dateTimeFormatter);
		return nickname + MESSAGE + formattedDate + DIARY_LIKE_MESSAGE;
	}
}
