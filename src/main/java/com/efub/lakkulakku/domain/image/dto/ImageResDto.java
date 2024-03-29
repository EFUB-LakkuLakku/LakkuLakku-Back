package com.efub.lakkulakku.domain.image.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ImageResDto {

	private UUID id;
	private Integer width;
	private Integer height;
	private Integer x;
	private Integer y;
	private Integer rotation;
	private String url;

	@Builder
	public ImageResDto(UUID id, Integer width, Integer height,
					   Integer x, Integer y, Integer rotation,
					   String url) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.rotation = rotation;
		this.x = x;
		this.y = y;
		this.url = url;
	}
}
