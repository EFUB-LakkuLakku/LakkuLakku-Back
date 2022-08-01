package com.efub.lakkulakku.domain.users.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.DUPLICATE_EMAIL;

public class DuplicateNicknameException extends IllegalArgumentException {
	public DuplicateNicknameException() {
		super(DUPLICATE_EMAIL);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
