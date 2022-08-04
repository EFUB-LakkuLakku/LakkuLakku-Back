package com.efub.lakkulakku.domain.sticker.dto;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.sticker.entity.Sticker;
import com.efub.lakkulakku.domain.sticker.repository.StickerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StickerMapper {

	private static StickerRepository stickerRepository;

	public StickerResDto toStickerResDto(Sticker entity) {

		if (entity == null)
			return null;

		return StickerResDto.builder()
				.id(entity.getId())
				.width(entity.getWidth())
				.height(entity.getHeight())
				.x(entity.getX())
				.y(entity.getY())
				.rotation(entity.getRotation())
				.category(entity.getCategory())
				.url(entity.getUrl())
				.build();
	}

//	public Sticker checkIsEntity(Diary diary, StickerResDto dto) {
//		Sticker sticker;
//
//		if (dto.getId() == null)
//			sticker = toEntity(diary, dto);
//		else
//			sticker = stickerRepository.findById(dto.getId()).orElseThrow(StickerNotFoundException::new);
//		return sticker;
//	}

	public Sticker toEntity(Diary diary, StickerReqDto dto) {
		if (diary == null || dto == null)
			return null;

		return Sticker.builder()
				.diary(diary)
				.width(dto.getWidth())
				.height(dto.getHeight())
				.x(dto.getX())
				.y(dto.getY())
				.rotation(dto.getRotation())
				.category(dto.getCategory())
				.url(dto.getUrl())
				.build();
	}

	public void deleteByDiary(Diary diary){
		stickerRepository.deleteAllByDiary(diary);
	}
}
