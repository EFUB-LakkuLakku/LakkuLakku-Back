package com.efub.lakkulakku.domain.notification.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.efub.lakkulakku.domain.users.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationEnum implements NotificationMessage {
	FRIEND("친구") {
		@Override
		public String getMessage(Users friendId, LocalDateTime date) {
			return friendId.getNickname() + friendMessage;
		}
	},
	COMMENT("댓글") {
		@Override
		public String getMessage(Users friendId, LocalDateTime date) {
			return friendId.getNickname() + "님이 나의 " + date.format(DateTimeFormatter.ofPattern("MM월 dd일"))
				+ commentMessage;
		}
	},
	LIKES("좋아요") {
		@Override
		public String getMessage(Users friendId, LocalDateTime date) {
			return friendId.getNickname() + "님이 나의 " + date.format(DateTimeFormatter.ofPattern("MM월 dd일"))
				+ likeMessage;
		}
	},
	RECOMMENT("대댓글") {
		@Override
		public String getMessage(Users friendId, LocalDateTime date) {
			return friendId.getNickname() + "님이 나의 " + date.format(DateTimeFormatter.ofPattern("MM월 dd일"))
				+ recoommentMessage;
		}
	};

	private String value;

	@Override
	public String toString() {
		return this.value;
	}
}
