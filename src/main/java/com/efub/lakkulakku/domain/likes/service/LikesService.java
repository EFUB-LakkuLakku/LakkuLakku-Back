package com.efub.lakkulakku.domain.likes.service;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.likes.dto.LikeClickResDto;
import com.efub.lakkulakku.domain.likes.dto.LikeMapper;
import com.efub.lakkulakku.domain.likes.dto.LikeReqDto;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.likes.repository.LikesRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDate;

import static com.efub.lakkulakku.global.constant.ResponseConstant.LIKES_ADD_SUCCESS;
import static com.efub.lakkulakku.global.constant.ResponseConstant.LIKES_DELETE_SUCCESS;

@Service
@RequiredArgsConstructor
public class LikesService {

	private final LikesRepository likesRepository;
	private final LikeMapper likeMapper;
	private final DiaryRepository diaryRepository;
	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public LikeClickResDto clickLike(Users user, LocalDate date, LikeReqDto likeReqDto) {

		Diary diary = diaryRepository.findById(likeReqDto.getDiaryId()).get();

		Likes likes;
		if (likesRepository.existsByDiaryAndUsers(diary, user)) {
			likes = likesRepository.findByDiaryAndUsers(diary, user).orElseThrow();
			likes.setIsLike();
			likesRepository.save(likes);

		} else {
			likes = likeMapper.toEntityFromReqDto(user, diary, likeReqDto);
			likesRepository.save(likes);
		}

		String message = likes.getIsLike() ? LIKES_ADD_SUCCESS : LIKES_DELETE_SUCCESS;

		if (message.equals(LIKES_ADD_SUCCESS)) {
			if (!user.getId().equals(diary.getUser().getId())) {
				notifyInfo(likes, "좋아요");
			}
			diary.setCntLike(diary.getCntLike() + 1);
			diaryRepository.save(diary);
		} else {
			diary.setCntLike(diary.getCntLike() - 1);
			diaryRepository.save(diary);
		}

		return LikeClickResDto.builder()
				.id(likes.getId())
				.diaryId(likes.getDiary().getId())
				.createdOn(likes.getCreatedOn())
				.message(message)
				.isLike(likes.getIsLike())
				.build();
	}

	private void notifyInfo(Likes likes, String notiType) {
		likes.publishEvent(eventPublisher, notiType);
	}
}