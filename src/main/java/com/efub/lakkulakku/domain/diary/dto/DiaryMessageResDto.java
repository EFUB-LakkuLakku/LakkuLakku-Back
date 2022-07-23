package com.efub.lakkulakku.domain.diary.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryMessageResDto {
	private String message;

	@Builder
	public DiaryMessageResDto(String message) {
		this.message = message;
	}
}
