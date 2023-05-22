package com.efub.lakkulakku.domain.likes.service;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.service.DiaryService;
import com.efub.lakkulakku.domain.likes.dto.LikeClickResDto;
import com.efub.lakkulakku.domain.likes.dto.LikeMapper;
import com.efub.lakkulakku.domain.likes.dto.LikeReqDto;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.likes.repository.LikesRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.domain.users.service.UsersService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class LikesServiceTest {

	@InjectMocks
	private LikesService likesService;

	@Mock
	private LikesRepository likesRepository;
	@Mock
	private LikeMapper likeMapper;
	@Mock
	private DiaryService diaryService;

	@Test
	@DisplayName("종아요_생성_성공")
	public void clickLikeTest() {
		LocalDate date = LocalDate.now();
		Users user = mock(Users.class);
		LikeReqDto likeReqDto = mock(LikeReqDto.class);
		Diary diary = createDiary(user);

		Likes likes = Likes.builder()
				.isLike(false)
				.diary(diary)
				.build();
		diary.setCntLike(1);

		when(likeReqDto.getDiaryId()).thenReturn(diary.getDiaryId());
		when(diaryService.findById(likeReqDto.getDiaryId())).thenReturn(diary);
		when(likesRepository.existsByDiaryAndUsers(diary, user)).thenReturn(false);
		when(likeMapper.toEntityFromReqDto(user, diary, likeReqDto)).thenReturn(likes);

		LikeClickResDto result = likesService.clickLike(user, date, likeReqDto);

		verify(likesRepository, times(1)).existsByDiaryAndUsers(diary, user);
		verify(likesRepository, times(1)).save(likes);
		verify(diaryService, times(1)).save(diary);
	}

	private Diary createDiary(Users user){
		LocalDate date = LocalDate.of(2023, 5, 9);

		Diary diary = Diary.builder()
				.user(user)
				.date(date)
				.build();


		return diary;
	}


}