package com.efub.lakkulakku.domain.notification.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.global.entity.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

	@Enumerated(EnumType.STRING)
	private NotificationEnum notiType;

	private String message;

	@Builder
	public Notification(Users receiver, NotificationEnum notiType, String message) {
		this.receiver = receiver;
		this.notiType = notiType;
		this.message = message;
	}

	public void makeMessage(Users friendId, LocalDateTime date) {
		this.message = notiType.getMessage(friendId, date);
	}
}
