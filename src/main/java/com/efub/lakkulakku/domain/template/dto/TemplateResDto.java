package com.efub.lakkulakku.domain.template.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class TemplateResDto {

	private UUID id;
	private String category;
	private String url;

	@Builder
	public TemplateResDto(UUID id, String category, String url) {
		this.id = id;
		this.category = category;
		this.url = url;
	}
}
