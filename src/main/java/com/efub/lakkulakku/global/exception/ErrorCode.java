package com.efub.lakkulakku.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    DUPLICATE_DIARY_DATE(CONFLICT, "C0001", "DUPLICATE_DIARY_EXISTS"),
    DUPLICATE_NICKNAME(CONFLICT, "C0002", "DUPLICATE_NICKNAME_EXISTS"),
    DUPLICATE_EMAIL(CONFLICT, "C0003", "DUPLICATE_EMAIL_EXISTS"),
    USER_NOT_FOUND(NOT_FOUND, "C0004", "CANNOT_FIND_USER"),
    DUPLICATE_FRIEND(CONFLICT, "C0004", "DUPLICATE_FRIEND_EXISTS"),
    USER_NOT_FOUND(NOT_FOUND, "C0005", "USER_NOT_FOUND");

    // Standard

    // Exception
    S3_UPLOAD_FAILURE(INTERNAL_SERVER_ERROR, "E0001", "NETWORK_FAILURE"),
    FILE_UPLOAD_FAILURE(BAD_REQUEST, "E0002", "WRONG_FILE_TYPE");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
