package com.efub.lakkulakku.domain.templateResource.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class TemplateResourceResDto {
	String category;
	List<TemplateResourceDto> templateList;

	@Builder
	public TemplateResourceResDto(String category, List<TemplateResourceDto> templateList) {
		this.category = category;
		this.templateList = templateList;
	}
}
