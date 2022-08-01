package com.efub.lakkulakku.domain.diary.dto;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryHomeMapper {

	public DiaryHomeResDto toDiaryHomeResDto(Diary entity) {
		if (entity == null)
			return null;

		return DiaryHomeResDto.builder()
				.id(entity.getId())
				.title(entity.getTitle())
				.titleEmoji(entity.getTitleEmoji())
				.date(entity.getDate())
				.build();
	}
}
