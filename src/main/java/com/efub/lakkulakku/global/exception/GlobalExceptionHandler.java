package com.efub.lakkulakku.global.exception;

import com.efub.lakkulakku.domain.diary.exception.BadDateRequestException;
import com.efub.lakkulakku.domain.diary.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.diary.exception.DuplicateDiaryException;
import com.efub.lakkulakku.domain.file.exception.FileExtenstionException;
import com.efub.lakkulakku.domain.file.exception.S3IOException;
import com.efub.lakkulakku.domain.friend.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.friend.exception.DuplicateFriendException;
import com.efub.lakkulakku.domain.users.exception.*;
//import com.efub.lakkulakku.domain.users.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/*================== Basic Exception ==================*/
	@ExceptionHandler(RuntimeException.class)
	protected final ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException e) {
		final ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST)
				.code(ErrorCode.RUNTIME_EXCEPTION)
				.message(e.getMessage())
				.build();
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@ExceptionHandler(DateTimeException.class)
	protected final ResponseEntity<ErrorResponse> handleDateTimeException(DateTimeException e) {
		final ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST)
				.code(ErrorCode.BAD_DATE_REQUEST)
				.message(e.getMessage())
				.build();
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	/*================== Diary Exception ==================*/
	@ExceptionHandler(DuplicateDiaryException.class)
	protected final ResponseEntity<ErrorResponse> handleDuplicateDiaryException(DuplicateDiaryException e) {
		final ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.CONFLICT)
				.code(ErrorCode.DUPLICATE_DIARY_DATE)
				.message(e.getMessage())
				.build();
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@ExceptionHandler(DiaryNotFoundException.class)
	protected final ResponseEntity<ErrorResponse> handleDiaryNotFoundException(DiaryNotFoundException e) {
		final ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.NOT_FOUND)
				.code(ErrorCode.DIARY_NOT_FOUND)
				.message(e.getMessage())
				.build();
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@ExceptionHandler(BadDateRequestException.class)
	protected final ResponseEntity<ErrorResponse> handleBadDateRequestException(BadDateRequestException e) {
		final ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST)
				.code(ErrorCode.BAD_DATE_REQUEST)
				.message(e.getMessage())
				.build();
		return ResponseEntity.status(response.getStatus()).body(response);
	}


	/*================== User Exception ==================*/
	@ExceptionHandler(DuplicateEmailException.class)
	protected final ResponseEntity<ErrorResponse> handleDuplicateEmailException(DuplicateEmailException e) {
		final ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST)
				.code(ErrorCode.DUPLICATE_EMAIL)
				.message(e.getMessage())
				.build();
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@ExceptionHandler(DuplicateNicknameException.class)
	protected final ResponseEntity<ErrorResponse> handleDuplicateNicknameException(DuplicateNicknameException e) {
		final ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST)
				.code(ErrorCode.DUPLICATE_NICKNAME)
				.message(e.getMessage())
				.build();
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@ExceptionHandler(PasswordNotMatchedException.class)
	protected final ResponseEntity<ErrorResponse> handlePasswordNotMatchedException(PasswordNotMatchedException e)
	{
		final ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST)
				.code(ErrorCode.PASSWORD_NOT_MATCH)
				.message(e.getMessage())
				.build();
		return ResponseEntity.status(response.getStatus()).body(response);

	}

	// 존재하지 않는 유저
	@ExceptionHandler(UserNotFoundException.class)
	protected final ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
		final ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.NOT_FOUND)
				.code(ErrorCode.USER_NOT_FOUND)
				.message(e.getMessage())
				.build();
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	/*================== Friend Exception ==================*/
	@ExceptionHandler(DuplicateFriendException.class)
	protected final ResponseEntity<ErrorResponse> handleDuplicateFriendException(DuplicateFriendException e) {
		final ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST)
				.code(ErrorCode.DUPLICATE_FRIEND)
				.message(e.getMessage())
				.build();
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	/*================== File Exception ==================*/
	// io 예외
	@ExceptionHandler(S3IOException.class)
	protected final ResponseEntity<ErrorResponse> handleS3IOException(S3IOException e) {
		final ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.code(ErrorCode.S3_UPLOAD_FAILURE)
				.message(e.getMessage())
				.build();
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	// 파일 형식 잘못된 경우
	@ExceptionHandler(FileExtenstionException.class)
	protected final ResponseEntity<ErrorResponse> handleFileExtenstionException(FileExtenstionException e) {
		final ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST)
				.code(ErrorCode.FILE_UPLOAD_FAILURE)
				.message(e.getMessage())
				.build();
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	/*================== Security Exception ==================*/
	@ExceptionHandler(BadTokenRequestException.class)
	protected final ResponseEntity<ErrorResponse> handleBadTokenRequestException(BadTokenRequestException e) {
		final ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST)
				.code(ErrorCode.TOKEN_VALIDATE_FAILURE)
				.message(e.getMessage())
				.build();
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@ExceptionHandler(TokenExpiredException.class)
	protected final ResponseEntity<ErrorResponse> handleTokenExpiredException(TokenExpiredException e) {
		final ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST)
				.code(ErrorCode.TOKEN_EXPIRED)
				.message(e.getMessage())
				.build();
		return ResponseEntity.status(response.getStatus()).body(response);
	}
}
