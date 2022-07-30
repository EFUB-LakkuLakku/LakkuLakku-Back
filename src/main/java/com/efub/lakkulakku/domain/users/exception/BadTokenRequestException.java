package com.efub.lakkulakku.domain.users.exception;

public class BadTokenRequestException extends RuntimeException {
	public BadTokenRequestException() {
		super("토큰을 확인하세요.");
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
