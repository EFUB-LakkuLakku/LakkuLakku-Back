package com.efub.lakkulakku.domain.diary.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryHomeResDto {

	private UUID id;
	private String title;
	private String titleEmoji;
	private String date;

	@Builder
	public DiaryHomeResDto(UUID id, String title, String titleEmoji, String date) {
		this.id = id;
		this.title = title;
		this.titleEmoji = titleEmoji;
		this.date = date;
	}
}
