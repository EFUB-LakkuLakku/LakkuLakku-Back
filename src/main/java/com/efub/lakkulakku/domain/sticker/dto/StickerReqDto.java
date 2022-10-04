package com.efub.lakkulakku.domain.sticker.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StickerReqDto {
	private Integer id;
	private Integer width;
	private Integer height;
	private Integer x;
	private Integer y;
	private Integer rotation;
	private String category;
	private String url;

	@Builder
	public StickerReqDto(Integer id, Integer width, Integer height,
						 Integer x, Integer y, Integer rotation,
						 String category, String url) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.rotation = rotation;
		this.category = category;
		this.url = url;
	}
}
