package com.efub.lakkulakku.domain.diary.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.DUPLICATE_DIARY;

public class DuplicateDiaryException extends IllegalArgumentException {
	public DuplicateDiaryException() {
		super(DUPLICATE_DIARY);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
