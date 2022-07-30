package com.efub.lakkulakku.domain.users.exception;


import static com.efub.lakkulakku.global.constant.ResponseConstant.EXPIRED_TOKEN;

public class TokenExpiredException extends RuntimeException {
	public TokenExpiredException() {
		super(EXPIRED_TOKEN);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}

