package com.efub.lakkulakku.domain.likes.controller;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.likes.dto.LikeResDto;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.likes.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.likes.service.LikesService;
import com.efub.lakkulakku.domain.likes.service.LikesService;
import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.efub.lakkulakku.global.constant.ResponseConstant.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/diaries")
public class LikesController {

	private final LikesService likesService;
	private final DiaryRepository diaryRepository;

	@PostMapping("/{date}/like")
	public ResponseEntity<?> createLike(@RequestBody @Valid LikeResDto likeResDto) {

		/*if (!diaryRepository.existsByDate(date))
			throw new DiaryNotFoundException();*/

		likesService.createLike(likeResDto);
		return ResponseEntity.ok(LIKES_ADD_SUCCESS);
	}

	@DeleteMapping("/{date}/like")
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> deleteLikesByUsersIdAndDiaryId(/*@PathVariable("id") */@RequestBody UUID userId, @RequestBody UUID diaryId, @RequestBody @Valid LikeResDto likeResDto) {

		/*if (!diaryRepository.existsByDate(date))
			throw new DiaryNotFoundException();*/

		likesService.deleteLikesByUsersIdAndDiaryId(userId, diaryId);
		return ResponseEntity.ok(LIKES_DELETE_SUCCESS);
	}
}