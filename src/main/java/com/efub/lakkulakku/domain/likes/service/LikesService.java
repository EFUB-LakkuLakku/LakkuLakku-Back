package com.efub.lakkulakku.domain.likes.service;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.likes.dto.LikeClickResDto;
import com.efub.lakkulakku.domain.likes.dto.LikeMapper;
import com.efub.lakkulakku.domain.likes.dto.LikeReqDto;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.likes.repository.LikesRepository;
import com.efub.lakkulakku.domain.notification.entity.Notification;
import com.efub.lakkulakku.domain.notification.repository.NotificationRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.efub.lakkulakku.global.constant.ResponseConstant.LIKES_ADD_SUCCESS;
import static com.efub.lakkulakku.global.constant.ResponseConstant.LIKES_DELETE_SUCCESS;

@Service
@RequiredArgsConstructor
public class LikesService {

	private final LikesRepository likesRepository;
	private final LikeMapper likeMapper;
	private final DiaryRepository diaryRepository;
	private final NotificationRepository notificationRepository;

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

		if (message == LIKES_ADD_SUCCESS) {
			if(!user.getId().equals(diary.getUser().getId()))
			{
				toLikeNotification(user, diary.getUser(), diary.getCreatedOn());
			}
			diary.setCntLike(diary.getCntLike()+1);
			diaryRepository.save(diary);
		}
		else {
			diary.setCntLike(diary.getCntLike()-1);
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

	@Transactional
	public void toLikeNotification(Users user, Users targetUser, LocalDateTime date)
	{
		Notification notification = Notification.builder()
				.userId(targetUser)
				.friendId(user)
				.notiType("좋아요")
				.build();
		notification.makeMessage(user, "좋아요", date);
		notificationRepository.save(notification);

	}
}