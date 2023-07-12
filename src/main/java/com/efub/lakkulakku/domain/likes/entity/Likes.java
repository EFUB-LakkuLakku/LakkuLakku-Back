package com.efub.lakkulakku.domain.likes.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.notification.dto.NotificationMessage;
import com.efub.lakkulakku.domain.notification.dto.NotificationReqDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.global.entity.BaseTimeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Likes extends BaseTimeEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 16)
	private UUID likeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diary_id")
	private Diary diary;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private Users users;

	@Column(columnDefinition = "boolean default 1")
	private Boolean isLike;

	@PrePersist
	public void prePersist() {
		this.isLike = true;
	}

	@Builder
	public Likes(Users users, Diary diary, boolean isLike) {
		this.users = users;
		this.diary = diary;
		this.isLike = isLike;
	}

	public void setIsLike() {
		this.isLike = !this.isLike;
	}

	public void publishEvent(ApplicationEventPublisher eventPublisher, String notiType) {
		eventPublisher.publishEvent(NotificationReqDto.builder()
			.receiver(diary.getUser())
			.notiType(notiType)
			.message(NotificationMessage.makeLikeNotification(users.getNickname(), diary.getCreatedOn()))
			.build());
		logger.info("좋아요 알림 발송");

	}

	private static final Logger logger = LoggerFactory.getLogger(Likes.class);
}
