package com.efub.lakkulakku.domain.diary.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.BAD_DATE_REQUEST;

public class BadDateRequestException extends RuntimeException {
	public BadDateRequestException() {
		super(BAD_DATE_REQUEST);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
