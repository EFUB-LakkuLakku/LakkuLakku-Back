package com.efub.lakkulakku.domain.users.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.EXPIRED_REFRESHTOKEN;

public class RefreshTokenExpiredException extends RuntimeException {
	public RefreshTokenExpiredException() {
		super(EXPIRED_REFRESHTOKEN);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
