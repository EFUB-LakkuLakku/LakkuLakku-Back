package com.efub.lakkulakku.domain.template.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class TemplateResDto {

	private UUID id;
	private String url;
	private String category;

	@Builder
	public TemplateResDto(UUID id, String url, String category) {
		this.id = id;
		this.url = url;
		this.category = category;
	}
}
