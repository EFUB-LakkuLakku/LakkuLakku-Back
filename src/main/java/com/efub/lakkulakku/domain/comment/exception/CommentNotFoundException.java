package com.efub.lakkulakku.domain.comment.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.*;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class CommentNotFoundException extends ResourceNotFoundException {

	public CommentNotFoundException() {
		super(NOTFOUND_COMMENT);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}