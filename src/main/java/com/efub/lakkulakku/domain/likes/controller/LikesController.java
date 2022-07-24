package com.efub.lakkulakku.domain.likes.controller;

import com.efub.lakkulakku.domain.likes.dto.LikeResDto;
import com.efub.lakkulakku.domain.likes.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.likes.service.LikesService;
import com.efub.lakkulakku.domain.likes.service.LikesService;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@EnableWebMvc
@RequestMapping("/api/v1/diaries")
public class LikesController {

	private final LikesService likesService;

	@PostMapping("/{date}/like")
	public ResponseEntity<?> likes(@RequestBody @Valid LikeResDto likeResDto
	) {

		/*if (!diaryRepository.existsByDate(date)) {
			throw new DiaryNotFoundException();
		} else {
			return ResponseEntity.ok("좋아요가 추가되었습니다");
		}*/


		return new ResponseEntity<>(likeResDto, HttpStatus.CREATED);
	}

	@DeleteMapping("/{date}/like")
	public ResponseEntity<?> unLikes(@RequestBody @Valid LikeResDto likeResDto
	) {

		/*if (!diaryRepository.existsByDate(date)) {
			throw new DiaryNotFoundException();
		} else {
			return ResponseEntity.ok("좋아요가 취소되었습니다");
		}*/


		return new ResponseEntity<>(likeResDto, HttpStatus.OK);
	}
}

