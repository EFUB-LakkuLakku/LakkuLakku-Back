package com.efub.lakkulakku.domain.comment.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.NO_AUTHORIZATION;

public class UnauthorizedException extends IllegalArgumentException {

	public UnauthorizedException() {
		super(NO_AUTHORIZATION);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}