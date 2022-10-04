package com.efub.lakkulakku.domain.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class LikeReqDto {

	private UUID diaryId;

	@Builder
	public LikeReqDto(UUID diaryId) {
		this.diaryId = diaryId;
	}
}