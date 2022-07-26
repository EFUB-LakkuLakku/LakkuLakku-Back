package com.efub.lakkulakku.domain.template.dto;

import com.efub.lakkulakku.domain.template.entity.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TemplateMapper {

	public TemplateResDto toTemplateResDto(Template entity) {

		if (entity == null)
			return null;

		return TemplateResDto.builder()
			.id(entity.getId())
			.url(entity.getUrl())
			.category(entity.getCategory())
			.build();
	}
}
