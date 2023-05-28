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
	private UUID notificationId;

	@ManyToOne
	@JoinColumn(name = "users_id") //알람을 받는 유저
	private Users receiver;

	private String notiType; //(comment, likes, follow)

	private String message;

	@Builder
	public Notification(Users receiver, String notiType, String message) {
		this.receiver = receiver;
		this.notiType = notiType;
		this.message = message;
	}

	public void makeMessage(Users friendId, String notiType, LocalDateTime date) {
		if (notiType.equals("친구")) {
			this.message = friendId.getNickname() + "님과 새 친구가 되었습니다.";
		} else if (notiType.equals("댓글") || notiType.equals("대댓글")) {
			this.message = friendId.getNickname() + "님이 나의 " + date.format(DateTimeFormatter.ofPattern("MM월 dd일"))+" 일기에 " + notiType +"을 달았습니다.";
		} else if (notiType.equals("좋아요")) {
			this.message = friendId.getNickname() + "님이 나의 " + date.format(DateTimeFormatter.ofPattern("MM월 dd일")) + " 일기에 좋아요를 눌렀습니다.";
		}
	}
}
