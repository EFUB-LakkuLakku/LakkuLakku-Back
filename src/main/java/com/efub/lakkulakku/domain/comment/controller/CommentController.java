package com.efub.lakkulakku.domain.comment.controller;

import static com.efub.lakkulakku.global.constant.ResponseConstant.*;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efub.lakkulakku.domain.comment.dto.CommentDeleteReqDto;
import com.efub.lakkulakku.domain.comment.dto.CommentReqDto;
import com.efub.lakkulakku.domain.comment.dto.CommentResDto;
import com.efub.lakkulakku.domain.comment.dto.CommentUpdateResDto;
import com.efub.lakkulakku.domain.comment.repository.CommentRepository;
import com.efub.lakkulakku.domain.comment.service.CommentService;
import com.efub.lakkulakku.domain.diary.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.diary.service.DiaryService;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.service.AuthUsers;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/diaries")
public class CommentController {
	private final CommentService commentService;
	private final CommentRepository commentRepository;
	private final DiaryService diaryService;

	@PostMapping("/{date}/comments")
	public ResponseEntity<CommentResDto> commentAdd(@AuthUsers Users user,
		@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
		@RequestBody CommentReqDto commentReqDto) {

		if (!diaryService.existsByDate(date)) {
			throw new DiaryNotFoundException();
		}

		CommentResDto commentResDto = commentService.addComment(user, date, commentReqDto);

		return ResponseEntity.ok(commentResDto);
	}

	@DeleteMapping("/{date}/comments")
	public ResponseEntity<String> commentRemove(@AuthUsers Users user,
		@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
		@RequestBody CommentDeleteReqDto commentDeleteReqDto) {

		commentService.removeComment(user, date, commentDeleteReqDto);

		return ResponseEntity.ok(COMMENT_DELETE_SUCCESS);

	}

	@PutMapping("/{date}/comments")
	public ResponseEntity<CommentUpdateResDto> update(@AuthUsers Users user,
		@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
		@RequestBody CommentReqDto commentReqDto) {

		CommentUpdateResDto commentUpdateResDto = commentService.update(user, date, commentReqDto);

		return ResponseEntity.ok(commentUpdateResDto);
	}
}
