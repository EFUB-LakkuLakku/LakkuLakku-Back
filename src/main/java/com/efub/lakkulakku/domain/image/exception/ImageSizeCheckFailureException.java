package com.efub.lakkulakku.domain.image.exception;

import java.io.IOException;

import static com.efub.lakkulakku.global.constant.ResponseConstant.SIZECHECK_FAILURE_IMAGE;

public class ImageSizeCheckFailureException extends IOException {
	public ImageSizeCheckFailureException() {
		super(SIZECHECK_FAILURE_IMAGE);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
