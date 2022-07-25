package com.efub.lakkulakku.domain.comment.controller;

import com.efub.lakkulakku.domain.comment.dto.CommentResDto;
import com.efub.lakkulakku.domain.comment.dto.CommentSaveDto;
import com.efub.lakkulakku.domain.comment.dto.CommentUpdateDto;
import com.efub.lakkulakku.domain.comment.exception.CommentNotFoundException;
import com.efub.lakkulakku.domain.comment.exception.ParentNotFoundException;
import com.efub.lakkulakku.domain.comment.exception.UnauthorizedException;
import com.efub.lakkulakku.domain.comment.service.CommentService;
import com.efub.lakkulakku.domain.diary.exception.DiaryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/diaries")
public class CommentController {

	@Autowired
	private final CommentService commentService;

	@PostMapping("/{date}/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity commentsSave(@PathVariable UUID id, @RequestBody CommentSaveDto commentSaveDto) {
		/*if (!diaryRepository.existsByDate(date))
			throw new DiaryNotFoundException();*/
		commentService.commentSave(id, commentSaveDto);
		return ResponseEntity.ok("해당 댓글이 작성되었습니다.");
	}


	@PostMapping("/{date}/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity recommentSave(@PathVariable UUID id, @PathVariable UUID parentId, @RequestBody CommentSaveDto commentSaveDto) {
		if (parentId == null)
			throw new ParentNotFoundException();
		commentService.recommentSave(id, parentId, commentSaveDto);
		return ResponseEntity.ok("해당 댓글이 작성되었습니다.");
	}


	@PutMapping("/{date}/comments")
	public ResponseEntity update(@PathVariable UUID id, @RequestBody @Valid CommentUpdateDto commentUpdateDto, HttpSession session) {
		/*if (!diaryRepository.isThisUserWriter(userId, diary.getId()))
			throw new UnauthorizedException();*/
		commentService.update(id, commentUpdateDto);
		return ResponseEntity.ok(id);
	}


	@DeleteMapping("/{date}/comments")
	public ResponseEntity delete(@PathVariable UUID id) {
		/*if (!commentRepository.findById(id))
			throw new CommentNotFoundException();*/
		commentService.delete(id);
		return ResponseEntity.ok("댓글이 삭제되었습니다.");
	}

}