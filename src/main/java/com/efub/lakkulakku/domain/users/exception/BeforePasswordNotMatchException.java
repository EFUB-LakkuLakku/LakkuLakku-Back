package com.efub.lakkulakku.domain.users.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.BEFORE_PASSWORD_NOT_MATCH;

public class BeforePasswordNotMatchException extends IllegalArgumentException {
	public BeforePasswordNotMatchException() {
		super(BEFORE_PASSWORD_NOT_MATCH);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
