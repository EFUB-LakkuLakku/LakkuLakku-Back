package com.efub.lakkulakku.domain.text.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class TextResDto {

	private UUID id;
	private String style;
	private String weight;
	private String size;
	private String width;
	private String height;
	private String align;
	private String color;
	private String content;
	private String xcoord;
	private String ycoord;
	private LocalDateTime createdOn;

	@Builder
	public TextResDto(UUID id, String style, String weight, String size,
					  String width, String height, String align, String color,
					  String content, String xcoord, String ycoord, LocalDateTime createdOn) {
		this.id = id;
		this.style = style;
		this.weight = weight;
		this.size = size;
		this.width = width;
		this.height = height;
		this.align = align;
		this.color = color;
		this.content = content;
		this.xcoord = xcoord;
		this.ycoord = ycoord;
		this.createdOn = createdOn;
	}
}
