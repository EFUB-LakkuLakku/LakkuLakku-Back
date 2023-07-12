package com.efub.lakkulakku.domain.friend.entity;

import com.efub.lakkulakku.domain.notification.dto.NotificationMessage;
import com.efub.lakkulakku.domain.notification.dto.NotificationReqDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.ApplicationEventPublisher;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Friend extends BaseTimeEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 16)
	private UUID friendId;

	@ManyToOne()
	//@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "users_id")
	private Users userId;

	@ManyToOne()
	@JoinColumn(name = "target_id")
	private Users targetId;

	@Builder
	public Friend(Users userId, Users targetId) {
		this.userId = userId;
		this.targetId = targetId;
	}

	public void publishEvent(ApplicationEventPublisher eventPublisher, String notiType){
		eventPublisher.publishEvent(NotificationReqDto.builder()
				.receiver(userId)
				.notiType(notiType)
				.message(NotificationMessage.makeFriendNotification(targetId.getNickname()))
				.build());

		eventPublisher.publishEvent(NotificationReqDto.builder()
				.receiver(targetId)
				.notiType(notiType)
				.message(NotificationMessage.makeFriendNotification(userId.getNickname()))
				.build());
	}
}
