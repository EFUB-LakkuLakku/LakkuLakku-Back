package com.efub.lakkulakku.domain.users.exception;


public class TokenExpiredException extends RuntimeException {
	public TokenExpiredException() {
		super("토큰이 만료되었습니다.");
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}

