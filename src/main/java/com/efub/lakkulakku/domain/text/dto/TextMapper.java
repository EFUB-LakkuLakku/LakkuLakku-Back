package com.efub.lakkulakku.domain.text.dto;

import com.efub.lakkulakku.domain.text.entity.Text;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class TextMapper {

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
			.xcoord(entity.getXcoord())
			.ycoord(entity.getYcoord())
			.createdOn(entity.getCreatedOn())
			.build();
	}
}
