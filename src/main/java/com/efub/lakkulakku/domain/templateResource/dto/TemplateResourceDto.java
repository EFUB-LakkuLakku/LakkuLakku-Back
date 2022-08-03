package com.efub.lakkulakku.domain.templateResource.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TemplateResourceDto {
	private Long id;
	private String category;
	private String url;

	@Builder
	public TemplateResourceDto(Long id, String category, String url) {
		this.id = id;
		this.category = category;
		this.url = url;
	}
}
