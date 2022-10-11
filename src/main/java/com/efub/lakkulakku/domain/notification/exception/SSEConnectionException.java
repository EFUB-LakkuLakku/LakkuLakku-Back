package com.efub.lakkulakku.domain.notification.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.SSE_CONNECTION_FAILURE_MSG;

public class SSEConnectionException extends IllegalArgumentException {
	public SSEConnectionException() {
		super(SSE_CONNECTION_FAILURE_MSG);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
