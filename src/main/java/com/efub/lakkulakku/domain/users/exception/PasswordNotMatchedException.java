package com.efub.lakkulakku.domain.users.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.PASSWORD_NOT_MATCH;

public class PasswordNotMatchedException extends IllegalArgumentException {

	public PasswordNotMatchedException() {
		super(PASSWORD_NOT_MATCH);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}