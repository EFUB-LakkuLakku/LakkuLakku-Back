package com.efub.lakkulakku.domain.text.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TextReqDto {

	private Integer id;
	private String style;
	private Integer weight;
	private Integer size;
	private Integer width;
	private Integer height;
	private String align;
	private String color;
	private String content;
	private Integer x;
	private Integer y;
	private Integer rotation;

	@Builder
	public TextReqDto(Integer id, String style, Integer weight, Integer size,
					  Integer width, Integer height,
					  String align, String color, String content,
					  Integer x, Integer y, Integer rotation) {
		this.id = id;
		this.style = style;
		this.weight = weight;
		this.size = size;
		this.width = width;
		this.height = height;
		this.align = align;
		this.color = color;
		this.content = content;
		this.x = x;
		this.y = y;
		this.rotation = rotation;
	}
}
