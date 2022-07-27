package com.efub.lakkulakku.domain.diary.exception;

public class DuplicateDiaryException extends IllegalArgumentException {
	public DuplicateDiaryException() {
		super("해당 날짜에 이미 다이어리가 존재합니다.");
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
