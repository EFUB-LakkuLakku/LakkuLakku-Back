package com.efub.lakkulakku.domain.image.dto;

import com.efub.lakkulakku.domain.image.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageMapper {

	public ImageResDto toImageResDto(Image entity) {

		if (entity == null)
			return null;

		return ImageResDto.builder()
				.id(entity.getId())
				.width(entity.getWidth())
				.height(entity.getHeight())
				.xcoord(entity.getXcoord())
				.ycoord(entity.getYcoord())
				.file(entity.getFile())
				.build();
	}
}
