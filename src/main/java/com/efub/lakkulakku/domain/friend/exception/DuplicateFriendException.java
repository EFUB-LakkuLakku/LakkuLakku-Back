package com.efub.lakkulakku.domain.friend.exception;

public class DuplicateFriendException extends IllegalArgumentException {
	public DuplicateFriendException() {
		super("이미 친구입니다.");
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
