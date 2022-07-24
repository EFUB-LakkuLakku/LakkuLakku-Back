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
	private UUID userId;
	private LocalDateTime createdOn;

	@Builder
	public LikeResDto(UUID id, UUID userId, LocalDateTime createdOn) {
		this.id = id;
		this.userId = userId;
		this.createdOn = createdOn;
	}
}