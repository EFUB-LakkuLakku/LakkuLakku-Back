package com.efub.lakkulakku.domain.comment.exception;

public class CommentNotFoundException extends IllegalArgumentException {

	public CommentNotFoundException() {
		super("존재하지 않는 댓글입니다.");
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}