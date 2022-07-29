package com.efub.lakkulakku.domain.comment.controller;

import com.efub.lakkulakku.domain.comment.dto.CommentResDto;
import com.efub.lakkulakku.domain.comment.exception.CommentNotFoundException;
import com.efub.lakkulakku.domain.comment.exception.ParentNotFoundException;
import com.efub.lakkulakku.domain.comment.service.CommentService;
import com.efub.lakkulakku.domain.diary.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.comment.entity.Comment;
import com.efub.lakkulakku.domain.comment.repository.CommentRepository;
import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/diaries")
public class CommentController {

	private final CommentService commentService;

	private final CommentRepository commentRepository;
	//private final DiaryRepository diaryRepository;

	@PostMapping("/{date}/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> commentAdd(/*@PathVariable UUID id,*/ @RequestBody CommentResDto commentResDto) {

		/*Diary diary = diaryRepository.findById(diaryId)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 다이어리 게시물입니다."));*/

		//LocalDateTime now = LocalDateTime.now();
		commentService.addComment(commentResDto);

		return ResponseEntity.ok("해당 댓글이 작성되었습니다.");
	}

	@DeleteMapping("/{date}/comments")
	public ResponseEntity<?> commentRemove(@PathVariable UUID id/*, @RequestBody CommentResDto commentResDto*/) {

		Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

		commentService.removeComment(id);

		return ResponseEntity.ok("댓글이 삭제되었습니다.");
	}

	/*@PutMapping("/{date}/comments")
	public ResponseEntity<?> commentEdit(@PathVariable UUID id, @Valid @RequestBody CommentResDto commentResDto) {

		/*Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("수정할 수 있는 권한이 없습니다."));

		commentService.update(id, commentResDto);

		return new ResponseEntity<>(HttpStatus.OK);
	}*/

	/*@PostMapping("/{date}/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> recommentAdd(@PathVariable UUID parentId/*, @RequestBody CCommentResDto commentResDto) {
		Comment comment = commentRepository.findById(parentId)
				.orElseThrow(() -> new IllegalArgumentException("상위 댓글이 존재하지 않습니다."));
		commentService.addRecomment(parentId, commentResDto);
		return ResponseEntity.ok("해당 댓글이 작성되었습니다.");
	}*/

	/*@DeleteMapping("/{date}/comments")
	public ResponseEntity<?> recommentRemove(@PathVariable UUID parentId) {
		commentService.removeRecomment(parentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{date}/comments")
	public ResponseEntity<?> recommentEdit(@PathVariable UUID id, @Valid @RequestBody CommentDto commentDto) {
		Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("수정할 수 있는 권한이 없습니다."));
		commentService.editRecomment(sessionUser.getUserId(), id, commentDto);
		return ResponseEntity.ok("댓글이 삭제되었습니다.");
	}*/ //대댓글 수정하기
}