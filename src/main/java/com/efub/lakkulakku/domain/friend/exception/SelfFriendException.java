package com.efub.lakkulakku.domain.friend.exception;

public class SelfFriendException extends IllegalArgumentException {
	public SelfFriendException() {
		super("자기 자신과 친구를 맺을 수 없습니다.");
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
