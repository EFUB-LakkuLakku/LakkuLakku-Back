package com.efub.lakkulakku.domain.comments.exception;

public class DiaryNotFoundException extends IllegalArgumentException {

    public DiaryNotFoundException() {
        super("존재하지 않는 다이어리 게시물입니다.");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}