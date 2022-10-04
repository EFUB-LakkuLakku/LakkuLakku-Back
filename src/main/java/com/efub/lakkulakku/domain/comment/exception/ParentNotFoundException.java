package com.efub.lakkulakku.domain.comment.exception;

import static com.efub.lakkulakku.global.constant.ResponseConstant.NOTFOUND_PARENT;

public class ParentNotFoundException extends IllegalArgumentException {

	public ParentNotFoundException() {
		super(NOTFOUND_PARENT);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}