package com.efub.lakkulakku.global.exception;

import com.efub.lakkulakku.domain.comment.exception.CommentNotFoundException;
import com.efub.lakkulakku.domain.comment.exception.ParentNotFoundException;
import com.efub.lakkulakku.domain.comment.exception.UnauthorizedException;
import com.efub.lakkulakku.domain.diary.exception.BadDateRequestException;
import com.efub.lakkulakku.domain.diary.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.diary.exception.DuplicateDiaryException;
import com.efub.lakkulakku.domain.file.exception.FileExtenstionException;
import com.efub.lakkulakku.domain.file.exception.S3IOException;
import com.efub.lakkulakku.domain.friend.exception.SelfFriendException;
import com.efub.lakkulakku.domain.friend.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.friend.exception.DuplicateFriendException;
import com.efub.lakkulakku.domain.notification.exception.SSEConnectionException;
import com.efub.lakkulakku.domain.users.exception.*;
import com.efub.lakkulakku.domain.image.exception.ImageFileMissingException;
import com.efub.lakkulakku.domain.image.exception.ImageNotFoundException;
import com.efub.lakkulakku.domain.image.exception.ImageSizeCheckFailureException;
import com.efub.lakkulakku.domain.users.exception.BadTokenRequestException;
import com.efub.lakkulakku.domain.users.exception.DuplicateEmailException;
import com.efub.lakkulakku.domain.users.exception.DuplicateNicknameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;
import java.util.HashMap;
import java.util.Map;

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

	// vaild 오류
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors()
				.forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}

	/*================== Diary Exception ==================*/
	@ExceptionHandler(DuplicateDiaryException.class)
	protected final ResponseEntity<ErrorResponse> handleDuplicateDiaryException(DuplicateDiaryException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.DUPLICATE_DIARY_DATE, e.getMessage());
	}

	@ExceptionHandler(DiaryNotFoundException.class)
	protected final ResponseEntity<ErrorResponse> handleDiaryNotFoundException(DiaryNotFoundException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.DIARY_NOT_FOUND, e.getMessage());
	}

	@ExceptionHandler(BadDateRequestException.class)
	protected final ResponseEntity<ErrorResponse> handleBadDateRequestException(BadDateRequestException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.BAD_DATE_REQUEST, e.getMessage());
	}

	/*================== Image Exception ==================*/
	@ExceptionHandler(ImageFileMissingException.class)
	protected final ResponseEntity<ErrorResponse> handleImageFileMissingException(ImageFileMissingException e){
		return ErrorResponse.toErrorResponseEntity(ErrorCode.IMAGE_MISSING, e.getMessage());
	}

	@ExceptionHandler(ImageNotFoundException.class)
	protected final ResponseEntity<ErrorResponse> handleImageNotFoundException(ImageNotFoundException e){
		return ErrorResponse.toErrorResponseEntity(ErrorCode.IMAGE_NOT_FOUND, e.getMessage());
	}

	@ExceptionHandler(ImageSizeCheckFailureException.class)
	protected final ResponseEntity<ErrorResponse> handleImageSizeCheckFailureException (ImageSizeCheckFailureException e){
		return ErrorResponse.toErrorResponseEntity(ErrorCode.IMAGE_SIZE_CHECK_FAILURE, e.getMessage());
	}

	/*================== User Exception ==================*/
	@ExceptionHandler(DuplicateEmailException.class)
	protected final ResponseEntity<ErrorResponse> handleDuplicateEmailException(DuplicateEmailException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.DUPLICATE_EMAIL, e.getMessage());
	}

	@ExceptionHandler(DuplicateNicknameException.class)
	protected final ResponseEntity<ErrorResponse> handleDuplicateNicknameException(DuplicateNicknameException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.DUPLICATE_NICKNAME, e.getMessage());
	}

	@ExceptionHandler(PasswordNotMatchedException.class)
	protected final ResponseEntity<ErrorResponse> handlePasswordNotMatchedException(PasswordNotMatchedException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.PASSWORD_NOT_MATCH, e.getMessage());

	}

	@ExceptionHandler(PasswordsNotEqualException.class)
	protected final ResponseEntity<ErrorResponse> handlePasswordsNotEqualException(PasswordsNotEqualException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.PASSWORDS_NOT_EQUAL, e.getMessage());

	}

	@ExceptionHandler(BeforePasswordNotMatchException.class)
	protected final ResponseEntity<ErrorResponse> handleBeforePasswordNotMatchException(BeforePasswordNotMatchException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.BEFORE_PASSWORD_NOT_MATCH, e.getMessage());

	}

	@ExceptionHandler(CertificationCodeMismatchException.class)
	protected final ResponseEntity<ErrorResponse> handleCertificationCodeMismatchException(CertificationCodeMismatchException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.CERTIFICATION_CODE_NOT_MATCH, e.getMessage());
	}

	// 존재하지 않는 유저
	@ExceptionHandler(UserNotFoundException.class)
	protected final ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.USER_NOT_FOUND, e.getMessage());
	}

	/*================== Friend Exception ==================*/
	@ExceptionHandler(DuplicateFriendException.class)
	protected final ResponseEntity<ErrorResponse> handleDuplicateFriendException(DuplicateFriendException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.DUPLICATE_FRIEND, e.getMessage());
	}

	@ExceptionHandler(SelfFriendException.class)
	protected final ResponseEntity<ErrorResponse> handleSelfFriendException(SelfFriendException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.SELF_FRIEND, e.getMessage());
	}

	/*================== Comment Exception ==================*/
	@ExceptionHandler(CommentNotFoundException.class)
	protected final ResponseEntity<ErrorResponse> handleCommentNotFoundException(CommentNotFoundException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.COMMENT_NOT_FOUND, e.getMessage());
	}

	@ExceptionHandler(ParentNotFoundException.class)
	protected final ResponseEntity<ErrorResponse> handleParentNotFoundException(ParentNotFoundException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.PARENT_NOT_FOUND, e.getMessage());
	}

	@ExceptionHandler(UnauthorizedException.class)
	protected final ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.UNAUTHORIZED_USER, e.getMessage());
	}

	/*================== File Exception ==================*/
	// io 예외
	@ExceptionHandler(S3IOException.class)
	protected final ResponseEntity<ErrorResponse> handleS3IOException(S3IOException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.S3_UPLOAD_FAILURE, e.getMessage());
	}

	// 파일 형식 잘못된 경우
	@ExceptionHandler(FileExtenstionException.class)
	protected final ResponseEntity<ErrorResponse> handleFileExtenstionException(FileExtenstionException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.FILE_UPLOAD_FAILURE, e.getMessage());
	}

	/*================== Token Exception ==================*/
	@ExceptionHandler(BadTokenRequestException.class)
	protected final ResponseEntity<ErrorResponse> handleBadTokenRequestException(BadTokenRequestException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.TOKEN_VALIDATE_FAILURE, e.getMessage());
	}

	@ExceptionHandler(RefreshTokenExpiredException.class)
	protected final ResponseEntity<ErrorResponse> handleAccessTokenExpiredException(RefreshTokenExpiredException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.REFRESHTOKEN_EXPIRED, e.getMessage());
	}

	/*================== SSE Exception ==================*/
	@ExceptionHandler(SSEConnectionException.class)
	protected final ResponseEntity<ErrorResponse> handleSSEConnectionException(SSEConnectionException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.SSE_CONNECTION_FAILURE, e.getMessage());
	}

}
