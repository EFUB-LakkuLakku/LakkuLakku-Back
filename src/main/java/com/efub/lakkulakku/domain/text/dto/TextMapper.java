package com.efub.lakkulakku.domain.text.dto;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.text.entity.Text;
import com.efub.lakkulakku.domain.text.repository.TextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TextMapper {

	private final TextRepository textRepository;

	public TextResDto toTextResDto(Text entity) {

		if (entity == null)
			return null;

		return TextResDto.builder()
				.id(entity.getId())
				.style(entity.getStyle())
				.weight(entity.getWeight())
				.size(entity.getSize())
				.width(entity.getWidth())
				.height(entity.getHeight())
				.align(entity.getAlign())
				.color(entity.getColor())
				.content(entity.getContent())
				.x(entity.getX())
				.y(entity.getY())
				.rotation(entity.getRotation())
				.build();
	}

//	public Text checkIsEntity(Diary diary, TextResDto dto) {
//		Text text;
//
//		if (dto.getId() == null)
//			text = toEntity(diary, dto);
//		else
//			text = textRepository.findById(dto.getId()).orElseThrow(TextNotFoundException::new);
//
//		return text;
//	}

	public Text toEntity(Diary diary, TextReqDto dto) {
		if (diary == null || dto == null)
			return null;

		return Text.builder()
				.diary(diary)
				.style(dto.getStyle())
				.weight(dto.getWeight())
				.size(dto.getSize())
				.width(dto.getWidth())
				.height(dto.getHeight())
				.align(dto.getAlign())
				.color(dto.getColor())
				.content(dto.getContent())
				.x(dto.getX())
				.y(dto.getY())
				.rotation(dto.getRotation())
				.build();
	}

	public void deleteByDiary(Diary diary) {
		textRepository.deleteAllByDiary(diary);
	}
}
