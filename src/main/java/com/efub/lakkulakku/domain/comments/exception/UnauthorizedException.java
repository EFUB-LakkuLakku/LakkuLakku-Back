package com.efub.lakkulakku.domain.comments.exception;

public class UnauthorizedException extends IllegalArgumentException {

    public UnauthorizedException() {
        super("수정할 수 있는 권한이 없습니다.");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}