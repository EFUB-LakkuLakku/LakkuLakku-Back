package com.efub.lakkulakku.domain.sticker.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class StickerResDto {

	private UUID id;
	private String width;
	private String height;
	private String xcoord;
	private String ycoord;
	private String category;
	private String url;

	@Builder
	public StickerResDto(UUID id, String width, String height,
						 String xcoord, String ycoord, String category, String url) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.xcoord = xcoord;
		this.ycoord = ycoord;
		this.category = category;
		this.url = url;
	}
}
