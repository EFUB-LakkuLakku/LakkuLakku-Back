package com.efub.lakkulakku.domain.diary.service;

import com.efub.lakkulakku.domain.comment.dto.CommentMapper;
import com.efub.lakkulakku.domain.comment.repository.CommentRepository;
import com.efub.lakkulakku.domain.diary.dto.DiaryMapper;
import com.efub.lakkulakku.domain.diary.dto.DiarySaveReqDto;
import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.likes.repository.LikesRepository;
import com.efub.lakkulakku.domain.template.entity.Template;
import com.efub.lakkulakku.domain.template.repository.TemplateRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.domain.users.service.UsersService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiaryServiceTest {
	@InjectMocks
	private DiaryService diaryService;

	@Mock
	private DiaryRepository diaryRepository;
	@Mock
	private TemplateRepository templateRepository;
	@Mock
	private CommentRepository commentRepository;
	@Mock
	private LikesRepository likesRepository;
	@Mock
	private DiaryMapper diaryMapper;
	@Mock
	private CommentMapper commentMapper;

	@Test
	public void createDiaryTest() {
		LocalDate diaryDate = LocalDate.now();
		Users user = mock(Users.class);
		when(diaryRepository.existsByDateAndUser(diaryDate, user)).thenReturn(false);

		diaryService.createDiary(user, diaryDate);

		verify(diaryRepository, times(1)).existsByDateAndUser(diaryDate, user);
		verify(diaryRepository, times(1)).save(any(Diary.class));
		verify(templateRepository, times(1)).save(any(Template.class));
	}

	@Test
	public void saveDiaryTest() throws IOException {
		LocalDate date = LocalDate.now();
		Users user = mock(Users.class);
		DiarySaveReqDto dto = mock(DiarySaveReqDto.class);
		Diary diary = mock(Diary.class);
		Diary savedDiary = mock(Diary.class);

		when(diaryRepository.findByDateAndUser(date, user)).thenReturn(java.util.Optional.of(diary));
		when(diaryMapper.saveDiary(diary, dto)).thenReturn(savedDiary);

		diaryService.saveDiary(date, user, dto);

		verify(diaryRepository, times(1)).findByDateAndUser(date, user);
		verify(diaryMapper, times(1)).saveDiary(diary, dto);
		verify(diaryRepository, times(1)).save(savedDiary);
	}

}