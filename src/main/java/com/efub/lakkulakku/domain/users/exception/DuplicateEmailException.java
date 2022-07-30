package com.efub.lakkulakku.domain.users.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.DUPLICATE_EMAIL;

public class DuplicateEmailException extends IllegalArgumentException {
	public DuplicateEmailException() {
		super(DUPLICATE_EMAIL);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
