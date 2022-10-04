package com.efub.lakkulakku.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class CommentDeleteReqDto {

	private UUID id;

	@Builder
	public CommentDeleteReqDto(UUID id) {

		this.id = id;
	}

}
