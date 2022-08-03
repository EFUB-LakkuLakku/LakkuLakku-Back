package com.efub.lakkulakku.domain.comment.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import static com.efub.lakkulakku.global.constant.ResponseConstant.NOTFOUND_COMMENT;

public class CommentNotFoundException extends ResourceNotFoundException {

	public CommentNotFoundException() {
		super(NOTFOUND_COMMENT);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}