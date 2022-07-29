package com.efub.lakkulakku.domain.users.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.NOTFOUND_USER;

public class UserNotFoundException extends IllegalArgumentException {

	public UserNotFoundException() {
		super(NOTFOUND_USER);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}