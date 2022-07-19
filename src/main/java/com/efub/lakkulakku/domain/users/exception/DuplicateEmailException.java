package com.efub.lakkulakku.domain.users.exception;

public class DuplicateEmailException extends IllegalArgumentException {
    public DuplicateEmailException() {
        super("중복된 이메일이 존재합니다.");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
