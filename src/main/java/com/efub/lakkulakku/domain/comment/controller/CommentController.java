package com.efub.lakkulakku.domain.comment.controller;

import com.efub.lakkulakku.domain.comment.dto.CommentResDto;
import com.efub.lakkulakku.domain.comment.exception.CommentNotFoundException;
import com.efub.lakkulakku.domain.comment.exception.ParentNotFoundException;
import com.efub.lakkulakku.domain.comment.entity.Comment;
import com.efub.lakkulakku.domain.comment.repository.CommentRepository;
import com.efub.lakkulakku.domain.comment.service.CommentService;
import com.efub.lakkulakku.domain.diary.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.service.AuthUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/diaries")
public class CommentController {

	private final CommentService commentService;
	private final CommentRepository commentRepository;
	private final DiaryRepository diaryRepository;

	@PostMapping("/{date}/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> commentAdd(@AuthUsers Users user, @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, /*UUID id, UUID parentId,*/ @RequestBody CommentResDto commentResDto) {

		if (!diaryRepository.existsByDate(date))
			throw new DiaryNotFoundException();

		commentService.addComment(commentResDto);

		return ResponseEntity.ok("해당 댓글이 작성되었습니다.");
	}

	@DeleteMapping("/{date}/comments/{id}")
	public ResponseEntity<?> commentRemove(@AuthUsers Users user, @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,@PathVariable("id") UUID id, @RequestBody CommentResDto commentResDto) {

		Comment comment = commentRepository.findById(id)
				.orElseThrow(CommentNotFoundException::new);

		commentService.removeComment(id);

		return ResponseEntity.ok("댓글이 삭제되었습니다.");
	}

	@PutMapping("/{date}/comments/{id}")
	public ResponseEntity update(@AuthUsers Users user, @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,@PathVariable("id") UUID id, @RequestBody CommentResDto commentResDto) {

		commentService.update(id, commentResDto);

		return ResponseEntity.ok(CommentUpdateResDto);
	}

	@PostMapping("/{date}/comments/{id}")
	public ResponseEntity<?> recommentAdd(@AuthUsers Users user, @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,@PathVariable("id") UUID id, UUID parentId, @RequestBody CommentResDto commentResDto) {

		//Comment comment = commentRepository.findById(id)
		//		.orElseThrow(ParentNotFoundException::new);

		if (!diaryRepository.existsByDate(date))
			throw new DiaryNotFoundException();

		Comment comment = commentRepository.findByParentId(parentId)
		      .orElseThrow(ParentNotFoundException::new);

		//commentService.addRecomment(commentResDto.getId(), commentResDto.getParentId(), commentResDto);

		commentService.addRecomment(id, parentId, commentResDto);

		return ResponseEntity.ok("해당 댓글이 작성되었습니다.");
	}

}