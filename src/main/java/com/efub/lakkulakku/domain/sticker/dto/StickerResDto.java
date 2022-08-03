package com.efub.lakkulakku.domain.sticker.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class StickerResDto {

	private UUID id;
	private Integer width;
	private Integer height;
	private Integer x;
	private Integer y;
	private String category;
	private String url;

	@Builder
	public StickerResDto(UUID id, Integer width, Integer height,
						 Integer x, Integer y, String category, String url) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.category = category;
		this.url = url;
	}
}
