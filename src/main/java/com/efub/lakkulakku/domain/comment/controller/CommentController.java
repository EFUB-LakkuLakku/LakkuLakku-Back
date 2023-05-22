package com.efub.lakkulakku.domain.comment.controller;

import com.efub.lakkulakku.domain.comment.dto.CommentDeleteReqDto;
import com.efub.lakkulakku.domain.comment.dto.CommentReqDto;
import com.efub.lakkulakku.domain.comment.dto.CommentResDto;
import com.efub.lakkulakku.domain.comment.dto.CommentUpdateResDto;
import com.efub.lakkulakku.domain.comment.entity.Comment;
import com.efub.lakkulakku.domain.comment.exception.ParentNotFoundException;
import com.efub.lakkulakku.domain.comment.exception.UnauthorizedException;
import com.efub.lakkulakku.domain.comment.repository.CommentRepository;
import com.efub.lakkulakku.domain.comment.service.CommentService;
import com.efub.lakkulakku.domain.diary.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.diary.service.DiaryService;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.service.AuthUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

import static com.efub.lakkulakku.global.constant.ResponseConstant.COMMENT_DELETE_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/diaries")
public class CommentController {
	private final CommentService commentService;
	private final CommentRepository commentRepository;
	private final DiaryService diaryService;

	@PostMapping("/{date}/comments")
	public ResponseEntity<?> commentAdd(@AuthUsers Users user, @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestBody CommentReqDto commentReqDto) {

		if (!diaryService.existsByDate(date))
			throw new DiaryNotFoundException();

		CommentResDto commentResDto = commentService.addComment(user, date, commentReqDto);

		return ResponseEntity.ok(commentResDto);
	}

	@DeleteMapping("/{date}/comments")
	public ResponseEntity<?> commentRemove(@AuthUsers Users user, @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestBody CommentDeleteReqDto commentDeleteReqDto) {

		commentService.removeComment(user, date, commentDeleteReqDto);

		return ResponseEntity.ok(COMMENT_DELETE_SUCCESS);

	}

	@PutMapping("/{date}/comments")
	public ResponseEntity<?> update(@AuthUsers Users user, @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestBody CommentReqDto commentReqDto) {

		CommentUpdateResDto commentUpdateResDto = commentService.update(user, date, commentReqDto);

		return ResponseEntity.ok(commentUpdateResDto);
	}

}