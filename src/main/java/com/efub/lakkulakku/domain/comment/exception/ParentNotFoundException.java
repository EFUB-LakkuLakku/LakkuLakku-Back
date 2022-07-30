package com.efub.lakkulakku.domain.comment.exception;

public class ParentNotFoundException extends IllegalArgumentException {

	public ParentNotFoundException() {
		super("상위 댓글이 존재하지 않습니다.");
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}