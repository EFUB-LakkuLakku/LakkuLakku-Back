package com.efub.lakkulakku.domain.users.exception;

public class PasswordNotMatchedException extends IllegalArgumentException {

    public PasswordNotMatchedException() {
        super("비밀번호가 일치하지 않습니다.");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}