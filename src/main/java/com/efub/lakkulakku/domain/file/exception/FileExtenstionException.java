package com.efub.lakkulakku.domain.file.exception;

public class FileExtenstionException extends IllegalArgumentException {

    public FileExtenstionException() {
        super("지원하지 않는 확장자명입니다. png, jpg, jpeg 파일로 업로드해주세요.");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}