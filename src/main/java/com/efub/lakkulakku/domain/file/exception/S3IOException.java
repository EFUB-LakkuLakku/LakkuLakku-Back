package com.efub.lakkulakku.domain.file.exception;

public class S3IOException extends IllegalArgumentException {

    public S3IOException() {
        super("파일 업로드에 실패했습니다. 네트워크를 확인해주세요.");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}