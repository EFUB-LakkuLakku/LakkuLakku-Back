package com.efub.lakkulakku.domain.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class LikeResDto {

	private UUID id;
	private LocalDateTime createdOn;
	private UUID userId;
	private UUID diaryId;
	private String message;
	private boolean isLike;

	@Builder
	public LikeResDto(UUID id, UUID userId, UUID diaryId, LocalDateTime createdOn, String message, boolean isLike) {
		this.id = id;
		this.userId = userId;
		this.diaryId = diaryId;
		this.createdOn = createdOn;
		this.message = message;
		this.isLike = isLike;
	}
}