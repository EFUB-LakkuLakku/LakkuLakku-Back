package com.efub.lakkulakku.domain.users.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.DUPLICATE_EMAIL;

public class CertificationCodeMismatchException extends IllegalArgumentException{
	public CertificationCodeMismatchException() {
	super(DUPLICATE_EMAIL);
}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
