package com.efub.lakkulakku.domain.friend.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
	public UserNotFoundException() {
		super("해당하는 사용자를 찾을 수 없습니다.");
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
