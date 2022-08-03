package com.efub.lakkulakku.domain.text.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import static com.efub.lakkulakku.global.constant.ResponseConstant.NOTFOUND_TEXT;

public class TextNotFoundException extends ResourceNotFoundException {

	public TextNotFoundException() {
		super(NOTFOUND_TEXT);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
