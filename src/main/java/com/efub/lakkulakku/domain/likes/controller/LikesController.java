package com.efub.lakkulakku.domain.likes.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.likes.dto.LikeClickResDto;
import com.efub.lakkulakku.domain.likes.dto.LikeReqDto;
import com.efub.lakkulakku.domain.likes.service.LikesService;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.service.AuthUsers;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/diaries")
public class LikesController {

	private final LikesService likesService;
	private final DiaryRepository diaryRepository;

	@PostMapping("/{date}/like")
	public ResponseEntity<LikeClickResDto> likeClick(@AuthUsers Users user,
		@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
		@RequestBody LikeReqDto likeReqDto) {

		LikeClickResDto likeClickResDto = likesService.clickLike(user, date, likeReqDto);
		return new ResponseEntity<>(likeClickResDto, HttpStatus.OK);
	}

}