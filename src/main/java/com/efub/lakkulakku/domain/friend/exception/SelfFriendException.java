package com.efub.lakkulakku.domain.friend.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.CONFLICT_SELF_FRIEND;

public class SelfFriendException extends IllegalArgumentException {
	public SelfFriendException() {
		super(CONFLICT_SELF_FRIEND);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
