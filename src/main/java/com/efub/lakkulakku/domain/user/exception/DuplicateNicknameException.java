package com.efub.lakkulakku.domain.user.exception;

public class DuplicateNicknameException extends IllegalArgumentException {
    public DuplicateNicknameException() {
        super("중복된 닉네임이 존재합니다.");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
