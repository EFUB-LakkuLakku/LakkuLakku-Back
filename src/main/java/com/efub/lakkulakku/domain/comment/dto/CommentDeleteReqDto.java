package com.efub.lakkulakku.domain.comment.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDeleteReqDto {

	private UUID commentId;

	@Builder
	public CommentDeleteReqDto(UUID commentId) {

		this.commentId = commentId;
	}

}
