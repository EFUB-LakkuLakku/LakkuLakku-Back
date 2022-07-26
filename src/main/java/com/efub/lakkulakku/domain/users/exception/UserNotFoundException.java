package com.efub.lakkulakku.domain.users.exception;

public class UserNotFoundException extends IllegalArgumentException {

    public UserNotFoundException() {
        super("해당 유저를 찾을 수 없습니다.");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}