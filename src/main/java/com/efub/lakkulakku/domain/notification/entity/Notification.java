package com.efub.lakkulakku.domain.notification.entity;

import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Notification extends BaseTimeEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 16)
	private UUID id;

	@OneToOne
	@JoinColumn(name = "users_id") //알람을 받는 유저
	private Users usersId;

	@OneToOne
	@JoinColumn(name = "friend_id") //알람을 발생시킨 유저
	private Users friendId;

	private String notiType; //(comment, likes, follow)

	// TODO : 알림 내용 column 필요
	private String message;

	@Builder
	public Notification(Users userId, Users friendId, String notiType) {
		this.usersId = userId; //알림 받음
		this.friendId = friendId; //알림 보냄
		this.notiType = notiType;
	}

	public void makeMessage(Users friendId, String notiType, LocalDateTime date) {
		if (notiType.equals("follow")) {
			this.message = friendId.getNickname() + "님과 새 친구가 되었습니다.";
		} else if (notiType.equals("comment")) {
			this.message = friendId.getNickname() + "님이 나의 " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))+" 일기에 댓글을 눌렀습니다.";
		} else if (notiType.equals("likes")) {
			this.message = friendId.getNickname() + "님이 나의 " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " 일기에 좋아요를 달았습니다.";
		}
	}
}
