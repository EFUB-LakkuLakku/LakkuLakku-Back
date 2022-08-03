package com.efub.lakkulakku.domain.likes.controller;

import com.efub.lakkulakku.domain.diary.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.likes.dto.LikeReqDto;
import com.efub.lakkulakku.domain.likes.dto.LikeResDto;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.likes.service.LikesService;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.service.AuthUsers;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/diaries")
public class LikesController {

	private final LikesService likesService;
	private final DiaryRepository diaryRepository;

	@PostMapping("/{date}/like")
	public ResponseEntity<?> createLike(@AuthUsers Users user, @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestBody LikeReqDto likeReqDto) {

		if (!diaryRepository.existsByDate(date))
			throw new DiaryNotFoundException();

		LikeResDto likeResDto = likesService.createLike(user, date, likeReqDto);
		return ResponseEntity.ok(likeResDto);
	}

}