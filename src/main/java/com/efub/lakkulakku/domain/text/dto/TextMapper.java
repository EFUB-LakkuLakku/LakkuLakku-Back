package com.efub.lakkulakku.domain.text.dto;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.text.entity.Text;
import com.efub.lakkulakku.domain.text.exception.TextNotFoundException;
import com.efub.lakkulakku.domain.text.repository.TextRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class TextMapper {

	private static TextRepository textRepository;

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
				.build();
	}

	public Text checkIsEntity(Diary diary, TextResDto dto) {
		Text text;

		if (dto.getId() == null)
			text = toEntity(diary, dto);
		else
			text = textRepository.findById(dto.getId()).orElseThrow(TextNotFoundException::new);

		return text;
	}

	public Text toEntity(Diary diary, TextResDto dto) {
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
				.build();
	}

	public Text deleteAndCreateEntity(Diary diary, TextResDto dto) {
		textRepository.deleteAllByDiaryId(diary.getId());
		return toEntity(diary, dto);
	}
}
