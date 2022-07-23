package com.efub.lakkulakku.domain.sticker.dto;

import com.efub.lakkulakku.domain.sticker.entity.Sticker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StickerMapper {
	public StickerResDto toStickerResDto(Sticker entity) {

		if (entity == null)
			return null;

		return StickerResDto.builder()
			.id(entity.getId())
			.width(entity.getWidth())
			.height(entity.getHeight())
			.xcoord(entity.getXcoord())
			.ycoord(entity.getYcoord())
			.category(entity.getCategory())
			.url(entity.getUrl())
			.build();
	}
}
