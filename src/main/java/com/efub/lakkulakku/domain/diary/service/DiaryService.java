package com.efub.lakkulakku.domain.diary.service;

import com.efub.lakkulakku.domain.diary.dto.DiaryLookupResDto;
import com.efub.lakkulakku.domain.diary.dto.DiaryMapper;
import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class DiaryService {

	private final DiaryRepository diaryRepository;
	private final DiaryMapper diaryMapper;
	private final UsersRepository userRepository;

	public DiaryLookupResDto getDiaryInfo(Diary diary) {
		return diaryMapper.toDiaryInfoResDto(diary);
	}

	@Transactional
	public void createDiary(LocalDate diaryDate) {
		//TODO : 현재 로그인한 유저를 받아와야 함
		Users user = userRepository.findByEmail("ewhaefub@gmail.com").get();
		Diary diary = Diary.builder()
				.user(user)
				.date(diaryDate)
				.title("")
				.titleEmoji(null)
				.comments(null)
				.template(null)
				.images(null)
				.likes(null)
				.texts(null)
				.stickers(null)
				.build();
		diaryRepository.save(diary);
	}

	@Transactional
	public void deleteDiary(LocalDate date) {
		Diary diary = diaryRepository.findByDate(date)
				.orElseThrow(DiaryNotFoundException::new);
		diaryRepository.delete(diary);
	}

	@Transactional
	public void updateDiary(LocalDate date, DiaryLookupResDto diaryLookupResDto) {
		Diary diary = diaryRepository.findByDate(date)
				.orElseThrow(DiaryNotFoundException::new);
		//		diaryRepository.save(diary);
		//diary.updateDiary(diaryInfoResDto.getDiary());
	}
}
