package com.efub.lakkulakku.domain.likes.controller;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.likes.dto.LikeResDto;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.likes.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.likes.repository.LikesRepository;
import com.efub.lakkulakku.domain.likes.service.LikesService;
import com.efub.lakkulakku.domain.likes.service.LikesService;
import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@EnableWebMvc
@RequestMapping("/api/v1/diaries")
public class LikesController {

	private final LikesService likesService;
	//private final DiaryRepository diaryRepository;
	//private final LikesRepository likesRepository;

	@PostMapping("/{date}/like")
	public ResponseEntity<?> likes(@RequestBody @Valid LikeResDto likeResDto
			/*,@Valid @PathVariable("date") String date*/) {

		/*if (!diaryRepository.existsByDate(date))
			throw new DiaryNotFoundException();*/

		likesService.likes(likeResDto);

		return ResponseEntity.ok("좋아요가 추가되었습니다");
	}

	@DeleteMapping("/{date}/like")
	public ResponseEntity<?> unLikes(@RequestBody @Valid LikeResDto likeResDto, UUID id
			/*, @Valid @PathVariable("date") String date*/) {

		/*if (!diaryRepository.existsByDate(date))
			throw new DiaryNotFoundException();*/

		likesService.unLikes(id);

		return ResponseEntity.ok("좋아요가 취소되었습니다");
	}
}