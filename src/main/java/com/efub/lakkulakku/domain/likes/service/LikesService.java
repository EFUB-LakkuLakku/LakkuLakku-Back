package com.efub.lakkulakku.domain.likes.service;

import com.efub.lakkulakku.domain.diary.dto.DiaryResDto;
import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.diary.service.DiaryService;
import com.efub.lakkulakku.domain.likes.dto.LikeClickResDto;
import com.efub.lakkulakku.domain.likes.dto.LikeMapper;
import com.efub.lakkulakku.domain.likes.dto.LikeReqDto;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.likes.repository.LikesRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;

import static com.efub.lakkulakku.global.constant.ResponseConstant.LIKES_ADD_SUCCESS;
import static com.efub.lakkulakku.global.constant.ResponseConstant.LIKES_DELETE_SUCCESS;

@Service
@RequiredArgsConstructor
@Transactional
public class LikesService {

	private final LikesRepository likesRepository;
	private final LikeMapper likeMapper;
	private final DiaryService diaryService;

	private final ApplicationEventPublisher eventPublisher;


	public LikeClickResDto clickLike(Users user, LocalDate date, LikeReqDto likeReqDto) {

		Diary diary = diaryService.findById(likeReqDto.getDiaryId());

		Likes likes;
		if (existsByDiaryAndUsers(diary, user)) {
			likes = findByDiaryAndUsers(diary, user);
			likes.setIsLike();
			likesRepository.save(likes);

		} else {
			likes = likeMapper.toEntityFromReqDto(user, diary, likeReqDto);
			likesRepository.save(likes);
		}

		String message = likes.getIsLike() ? LIKES_ADD_SUCCESS : LIKES_DELETE_SUCCESS;

		if (message.equals(LIKES_ADD_SUCCESS)) {
			if (!user.getUserId().equals(diary.getUser().getUserId())) {
				notifyInfo(likes, "좋아요");
			}
			diary.setCntLike(diary.getCntLike() + 1);
			diaryService.save(diary);
		}
		else {
			diary.setCntLike(diary.getCntLike() - 1);
			diaryService.save(diary);
		}

		return LikeClickResDto.builder()
				.id(likes.getLikeId())
				.diaryId(likes.getDiary().getDiaryId())
				.createdOn(likes.getCreatedOn())
				.message(message)
				.isLike(likes.getIsLike())
				.build();
	}

	@Transactional(readOnly = true)
	public Likes findByDiaryAndUsers(Diary diary, Users user){
		return likesRepository.findByDiaryAndUsers(diary, user).orElseThrow((DiaryNotFoundException::new));
	}

	@Transactional(readOnly = true)
	public boolean existsByDiaryAndUsers(Diary diary, Users user){
		return likesRepository.existsByDiaryAndUsers(diary, user);
	}




	private void notifyInfo(Likes likes, String notiType) {
		likes.publishEvent(eventPublisher, notiType);
	}
}