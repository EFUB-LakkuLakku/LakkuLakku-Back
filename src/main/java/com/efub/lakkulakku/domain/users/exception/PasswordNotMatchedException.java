package com.efub.lakkulakku.domain.users.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.NOTMATCH_PASSWORD;

public class PasswordNotMatchedException extends IllegalArgumentException {

	public PasswordNotMatchedException() {
		super(NOTMATCH_PASSWORD);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}