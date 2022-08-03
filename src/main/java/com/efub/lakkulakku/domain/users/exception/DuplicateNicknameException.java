package com.efub.lakkulakku.domain.users.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.DUPLICATE_NICKNAME;

public class DuplicateNicknameException extends IllegalArgumentException {
	public DuplicateNicknameException() {
		super(DUPLICATE_NICKNAME);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
