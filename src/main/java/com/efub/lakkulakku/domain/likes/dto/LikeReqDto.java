package com.efub.lakkulakku.domain.likes.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeReqDto {

	private UUID diaryId;

	@Builder
	public LikeReqDto(UUID diaryId) {
		this.diaryId = diaryId;
	}
}