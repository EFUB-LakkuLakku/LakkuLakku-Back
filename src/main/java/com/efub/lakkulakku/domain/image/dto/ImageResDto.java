package com.efub.lakkulakku.domain.image.dto;

import com.efub.lakkulakku.domain.file.entity.File;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ImageResDto {

	private UUID id;
	private String width;
	private String height;
	private String xcoord;
	private String ycoord;
	private File file;

	@Builder
	public ImageResDto(UUID id, String width, String height, String xcoord, String ycoord, File file) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.xcoord = xcoord;
		this.ycoord = ycoord;
		this.file = file;
	}
}
