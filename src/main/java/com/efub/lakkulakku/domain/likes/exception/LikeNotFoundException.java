package com.efub.lakkulakku.domain.likes.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.*;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class LikeNotFoundException extends ResourceNotFoundException {
	public LikeNotFoundException() {
		super(NOTFOUND_LIKE);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
