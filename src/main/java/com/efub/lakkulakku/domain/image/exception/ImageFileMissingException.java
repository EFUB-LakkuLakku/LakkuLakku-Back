
package com.efub.lakkulakku.domain.image.exception;

import java.io.IOException;

import static com.efub.lakkulakku.global.constant.ResponseConstant.MISSING_IMAGE;

public class ImageFileMissingException extends IOException {
	public ImageFileMissingException() {
		super(MISSING_IMAGE);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}