package com.efub.lakkulakku.domain.users.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.BAD_TOKEN_REQUEST;

public class BadTokenRequestException extends RuntimeException {
	public BadTokenRequestException() {
		super(BAD_TOKEN_REQUEST);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
