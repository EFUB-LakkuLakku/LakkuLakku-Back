package com.efub.lakkulakku.domain.image.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import static com.efub.lakkulakku.global.constant.ResponseConstant.NOTFOUND_IMAGE;

public class ImageNotFoundException extends ResourceNotFoundException {
	public ImageNotFoundException() {
		super(NOTFOUND_IMAGE);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
