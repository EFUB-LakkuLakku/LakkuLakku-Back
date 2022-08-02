package com.efub.lakkulakku.domain.users.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.PASSWORDS_NOT_EQUAL;


public class PasswordsNotEqualException extends IllegalArgumentException {

	public PasswordsNotEqualException() {
		super(PASSWORDS_NOT_EQUAL);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
