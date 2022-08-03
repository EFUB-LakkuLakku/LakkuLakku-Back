package com.efub.lakkulakku.domain.likes.service;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.likes.dto.LikeReqDto;
import com.efub.lakkulakku.domain.likes.dto.LikeResDto;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.likes.repository.LikesRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.domain.users.service.AuthUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.efub.lakkulakku.global.constant.ResponseConstant.LIKES_ADD_SUCCESS;
import static com.efub.lakkulakku.global.constant.ResponseConstant.LIKES_DELETE_SUCCESS;

@Service
@RequiredArgsConstructor
public class LikesService {

	private final LikesRepository likesRepository;
	private final DiaryRepository diaryRepository;

	@Transactional
	public LikeResDto createLike(Users user, LocalDate date, LikeReqDto likeReqDto) {

		Diary diary = diaryRepository.findById(likeReqDto.getDiaryId()).get();

		if (likesRepository.existsByDiaryAndUsers(diary, user)) { // 좋아요 존재하면

			Likes likes = likesRepository.findByDiaryAndUsers(diary, user).orElseThrow();
			likes.setIsLike();
			String message = likes.getIsLike() ? LIKES_ADD_SUCCESS : LIKES_DELETE_SUCCESS;
			return LikeResDto.builder()
					.id(likes.getId())
					.diaryId(likes.getDiary().getId())
					.createdOn(likes.getCreatedOn())
					.message(message)
					.isLike(likes.getIsLike())
					.build();
		}

		// 존재하지 않으면 like 객체 생성
		else {

			Likes likes = likesRepository.findByDiaryAndUsers(diary, user).orElseThrow();

			likesRepository.save(likes);
			String message = likes.getIsLike() ? LIKES_ADD_SUCCESS : LIKES_DELETE_SUCCESS;
			return LikeResDto.builder()
					.id(likes.getId())
					.diaryId(likes.getDiary().getId())
					.createdOn(likes.getCreatedOn())
					.message(message)
					.isLike(likes.getIsLike())
					.build();
		}

	}

	private Optional<Likes> findById(LikeResDto likeResDto) {
		return likesRepository
				.findById(likeResDto.getId());

	}

}