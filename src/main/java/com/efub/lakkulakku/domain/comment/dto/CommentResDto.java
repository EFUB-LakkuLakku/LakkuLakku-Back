package com.efub.lakkulakku.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class CommentResDto {

	private UUID id;
	private UUID userId;
	private String profileImageUrl;
	private String nickname;
	private UUID parentId;
	private String content;
	private Boolean isSecret;
	private LocalDateTime createdOn;
	private String message;

	@Builder
	public CommentResDto(UUID id, UUID userId, String profileImageUrl, String nickname, UUID parentId, String content,
						 Boolean isSecret, LocalDateTime createdOn, String message) {
		this.id = id;
		this.userId = userId;
		this.profileImageUrl = profileImageUrl;
		this.nickname = nickname;
		this.parentId = parentId;
		this.content = content;
		this.isSecret = isSecret;
		this.createdOn = createdOn;
		this.message = message;
	}

}
