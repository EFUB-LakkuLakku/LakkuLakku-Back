package com.efub.lakkulakku.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	// Common
	DUPLICATE_DIARY_DATE(CONFLICT, "C0001", "DUPLICATE_DIARY_EXISTS"),
	DUPLICATE_NICKNAME(CONFLICT, "C0002", "DUPLICATE_NICKNAME_EXISTS"),
	DUPLICATE_EMAIL(CONFLICT, "C0003", "DUPLICATE_EMAIL_EXISTS"),
	DUPLICATE_FRIEND(CONFLICT, "C0004", "DUPLICATE_FRIEND_EXISTS"),
	USER_NOT_FOUND(NOT_FOUND, "C0005", "USER_NOT_FOUND"),

	// Standard

    // Exception
    S3_UPLOAD_FAILURE(INTERNAL_SERVER_ERROR, "E0001", "NETWORK_FAILURE"),
    FILE_UPLOAD_FAILURE(BAD_REQUEST, "E0002", "WRONG_FILE_TYPE"),
    TOKEN_VALIDATE_FAILURE(BAD_REQUEST, "E1001", "INVALID_TOKEN"),
    TOKEN_EXPIRED(BAD_REQUEST, "E1002", "TOKEN_EXPIRED"),

    //SUCCESS
    LOGOUT_SUCCESS(OK, "S0001", "LOGOUT_SUCCESS");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
