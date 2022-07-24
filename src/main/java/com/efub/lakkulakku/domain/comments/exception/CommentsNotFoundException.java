package com.efub.lakkulakku.domain.comments.exception;

public class CommentsNotFoundException extends IllegalArgumentException {

    public CommentsNotFoundException(String s) {
        super("존재하지 않는 댓글입니다.");
    }


    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

