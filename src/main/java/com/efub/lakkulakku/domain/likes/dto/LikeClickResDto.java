package com.efub.lakkulakku.domain.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class LikeClickResDto {
	private UUID id;
	private LocalDateTime createdOn;
	private UUID diaryId;
	private String message;
	private boolean isLike;

	@Builder
	public LikeClickResDto(UUID id, UUID diaryId, LocalDateTime createdOn, String message, boolean isLike) {
		this.id = id;
		this.diaryId = diaryId;
		this.createdOn = createdOn;
		this.message = message;
		this.isLike = isLike;
	}
}
