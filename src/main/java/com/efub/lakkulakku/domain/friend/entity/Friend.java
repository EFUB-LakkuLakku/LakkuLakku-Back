package com.efub.lakkulakku.domain.friend.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.ApplicationEventPublisher;

import com.efub.lakkulakku.domain.notification.dto.NotificationMessage;
import com.efub.lakkulakku.domain.notification.dto.NotificationReqDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.global.entity.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Friend extends BaseTimeEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 16)
	private UUID id;

	@ManyToOne()
	//@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "sender_id")
	private Users sender;

	@ManyToOne()
	@JoinColumn(name = "target_id")
	private Users target;

	@Builder
	public Friend(Users sender, Users target) {
		this.sender = sender;
		this.target = target;
	}

	public void publishEvent(ApplicationEventPublisher eventPublisher, String notiType) {
		eventPublisher.publishEvent(NotificationReqDto.builder()
			.receiver(sender)
			.notiType(notiType)
			.message(NotificationMessage.makeFriendNotification(target.getNickname()))
			.build());

		eventPublisher.publishEvent(NotificationReqDto.builder()
			.receiver(target)
			.notiType(notiType)
			.message(NotificationMessage.makeFriendNotification(sender.getNickname()))
			.build());
	}
}
