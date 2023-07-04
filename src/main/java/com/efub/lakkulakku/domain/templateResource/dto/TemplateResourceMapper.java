package com.efub.lakkulakku.domain.templateResource.dto;

import com.efub.lakkulakku.domain.templateResource.entity.TemplateResource;
import org.springframework.stereotype.Component;

@Component
public class TemplateResourceMapper {

	public TemplateResourceDto toTemplateResourceDto(TemplateResource entity) {
		if (entity == null)
			return null;

		return TemplateResourceDto.builder()
				.id(entity.getTemplateResourceId())
				.url(entity.getUrl())
				.category(entity.getCategory())
				.build();
	}
}
