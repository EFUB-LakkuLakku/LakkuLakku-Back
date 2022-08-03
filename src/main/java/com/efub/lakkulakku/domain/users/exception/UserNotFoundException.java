package com.efub.lakkulakku.domain.users.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import static com.efub.lakkulakku.global.constant.ResponseConstant.NOTFOUND_USER;

public class UserNotFoundException extends ResourceNotFoundException {

	public UserNotFoundException() {
		super(NOTFOUND_USER);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}