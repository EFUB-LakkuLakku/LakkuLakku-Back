package com.efub.lakkulakku.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class CommentUpdateResDto {

	private UUID id;
	private String profileImageUrl;
	private String nickname;
	private UUID parentId;
	private String content;
	private Boolean isSecret;
	private LocalDateTime createdOn;

	@Builder
	public CommentUpdateResDto(UUID id, String profileImageUrl, String nickname, UUID parentId, String content,
						 Boolean isSecret, LocalDateTime createdOn) {
		this.id = id;
		this.profileImageUrl = profileImageUrl;
		this.nickname = nickname;
		this.parentId = parentId;
		this.content = content;
		this.isSecret = isSecret;
		this.createdOn = createdOn;

	}

}

