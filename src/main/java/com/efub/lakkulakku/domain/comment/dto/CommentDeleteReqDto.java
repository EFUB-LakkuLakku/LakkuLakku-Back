package com.efub.lakkulakku.domain.comment.dto;

import lombok.Builder;

import java.util.UUID;

public class CommentDeleteReqDto {

	private UUID id;

	@Builder
	public CommentDeleteReqDto(UUID id) {

		this.id = id;
	}

}
