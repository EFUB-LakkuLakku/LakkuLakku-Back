package com.efub.lakkulakku.domain.text.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class TextResDto {

	private UUID id;
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

	@Builder
	public TextResDto(UUID id, String style, Integer weight, Integer size,
					  Integer width, Integer height, String align, String color,
					  String content, Integer x, Integer y) {
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
	}
}
