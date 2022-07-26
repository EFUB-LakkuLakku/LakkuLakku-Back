package com.efub.lakkulakku.domain.diary.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryResDto {

	private UUID id;
	private UUID userId;
	private String date;
	private String title;
	private String titleEmoji;
	private UUID templateId;
	private Integer cntComment;
	private Integer cntLike;

	@Builder
	public DiaryResDto(UUID id, UUID userId, String date,
					   String title, String titleEmoji, UUID templateId,
					   Integer cntComment, Integer cntLike) {
		this.id = id;
		this.userId = userId;
		this.date = date;
		this.title = title;
		this.titleEmoji = titleEmoji;
		this.templateId = templateId;
		this.cntComment = cntComment;
		this.cntLike = cntLike;
	}
}
