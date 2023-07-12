package com.efub.lakkulakku.domain.comment.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentReqDto {
	private UUID commentId;

	private UUID diaryId;
	private String content;
	private boolean isSecret;
	private UUID parentId;

	@Builder
	public CommentReqDto(UUID commentId, UUID diaryId, String content, boolean isSecret, UUID parentId) {
		this.commentId = commentId;
		this.diaryId = diaryId;
		this.content = content;
		this.isSecret = isSecret;
		this.parentId = parentId;
	}
}
