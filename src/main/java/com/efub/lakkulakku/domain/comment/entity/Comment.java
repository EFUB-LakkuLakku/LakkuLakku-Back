package com.efub.lakkulakku.domain.comment.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
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
public class Comment extends BaseTimeEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 16)
	private UUID commentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users_id")
	private Users users;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diary_id")
	private Diary diary;

	@Column(length = 500)
	@NotNull
	private String content;

	@Column
	private UUID parentId;

	@Column(columnDefinition = "boolean default 0")
	private Boolean isSecret;

	@PrePersist
	public void prePersist() {
		this.isSecret = this.isSecret != null && this.isSecret;
	}

	public void update(String content) {
		this.content = content;
	}

	@Builder
	public Comment(Diary diary, Users users, String content, UUID parentId, Boolean isSecret) {
		this.diary = diary;
		this.users = users;
		this.content = content;
		this.parentId = parentId;
		this.isSecret = isSecret;
	}

	public void publishEvent(ApplicationEventPublisher eventPublisher, String notiType) {
		eventPublisher.publishEvent(NotificationReqDto.builder()
			.receiver(diary.getUser())
			.notiType(notiType)
			.message(NotificationMessage.makeCommentNotification(users.getNickname(), diary.getCreatedOn()))
			.build());
	}
}