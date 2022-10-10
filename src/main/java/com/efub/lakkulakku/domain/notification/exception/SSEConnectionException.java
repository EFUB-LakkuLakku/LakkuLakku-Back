package com.efub.lakkulakku.domain.notification.exception;

public class SSEConnectionException extends IllegalArgumentException {
	public SSEConnectionException() {
		super("알림 연결에 실패했습니다.");
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
