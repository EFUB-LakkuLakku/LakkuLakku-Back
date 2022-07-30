package com.efub.lakkulakku.domain.diary.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import static com.efub.lakkulakku.global.constant.ResponseConstant.NOTFOUND_DIARY;

public class DiaryNotFoundException extends ResourceNotFoundException {
	public DiaryNotFoundException() {
		super(NOTFOUND_DIARY);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
