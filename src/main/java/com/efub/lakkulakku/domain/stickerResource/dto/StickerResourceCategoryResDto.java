package com.efub.lakkulakku.domain.stickerResource.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class StickerResourceCategoryResDto {
	private String category;
	private List<StickerResourceResDto> stickerList;

	@Builder
	public StickerResourceCategoryResDto(String category, List<StickerResourceResDto> stickerList){
		this.category = category;
		this.stickerList = stickerList;
	}
}
