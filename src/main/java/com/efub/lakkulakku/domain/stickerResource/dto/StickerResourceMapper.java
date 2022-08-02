package com.efub.lakkulakku.domain.stickerResource.dto;

import com.efub.lakkulakku.domain.stickerResource.entity.StickerResource;
import org.springframework.stereotype.Component;

@Component
public class StickerResourceMapper {

	public StickerResourceResDto toStickerResourceResDto(StickerResource entity) {

		if (entity == null)
			return null;

		return StickerResourceResDto.builder()
				.id(entity.getId())
				.category(entity.getCategory())
				.url(entity.getUrl())
				.build();
	}
}
