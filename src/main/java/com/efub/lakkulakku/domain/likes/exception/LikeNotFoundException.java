package com.efub.lakkulakku.domain.likes.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import static com.efub.lakkulakku.global.constant.ResponseConstant.NOTFOUND_LIKE;

public class LikeNotFoundException extends ResourceNotFoundException {
	public LikeNotFoundException() {
		super(NOTFOUND_LIKE);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
