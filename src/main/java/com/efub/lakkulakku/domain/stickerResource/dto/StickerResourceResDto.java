package com.efub.lakkulakku.domain.stickerResource.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StickerResourceResDto {
	private Long id;
	private String category;
	private String url;

	@Builder
	public StickerResourceResDto(Long id, String category, String url) {
		this.id = id;
		this.category = category;
		this.url = url;
	}
}
