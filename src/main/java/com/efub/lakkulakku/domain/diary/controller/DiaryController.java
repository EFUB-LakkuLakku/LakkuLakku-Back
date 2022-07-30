package com.efub.lakkulakku.domain.diary.controller;

import com.efub.lakkulakku.domain.diary.dto.DiaryLookupResDto;
import com.efub.lakkulakku.domain.diary.dto.DiaryMessageResDto;
import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.exception.BadDateRequestException;
import com.efub.lakkulakku.domain.diary.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.diary.exception.DuplicateDiaryException;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static com.efub.lakkulakku.global.constant.ResponseConstant.*;

@RestController
@RequestMapping("/api/v1/diaries/{date}")
@RequiredArgsConstructor
public class DiaryController {

	private final DiaryService diaryService;
	private final DiaryRepository diaryRepository;

	@GetMapping
	public ResponseEntity<DiaryLookupResDto> getDiaryByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		LocalDate END_DATE = LocalDate.of(2099, 12, 31);
		LocalDate START_DATE = LocalDate.of(1970, 1, 1);

		if (date.isBefore(START_DATE) || date.isAfter(END_DATE))
			throw new BadDateRequestException();

		if (!diaryRepository.existsByDate(date))
			return ResponseEntity.ok()
					.body(new DiaryLookupResDto(null, null, null, null, null, null));

		Diary diary = diaryRepository.findByDate(date).orElseThrow(DiaryNotFoundException::new);
		return ResponseEntity.ok()
				.body(diaryService.getDiaryInfo(diary));
	}

	@PostMapping
	public ResponseEntity<DiaryMessageResDto> createDiary(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		if (diaryRepository.existsByDate(date))
			throw new DuplicateDiaryException();
		diaryService.createDiary(date);
		return ResponseEntity.ok().body(new DiaryMessageResDto(date + DIARY_CREATE_SUCCESS));
	}

	@DeleteMapping
	public ResponseEntity<DiaryMessageResDto> deleteDiary(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		diaryService.deleteDiary(date);
		return ResponseEntity.ok().body(new DiaryMessageResDto(date + DIARY_DELETE_SUCCESS));
	}

	@PostMapping("/save")
	public ResponseEntity<DiaryMessageResDto> updateDiary(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
														  @RequestBody DiaryLookupResDto diaryLookupResDto) {
		diaryService.updateDiary(date, diaryLookupResDto);
		return ResponseEntity.ok().body(new DiaryMessageResDto(date + DIARY_UPDATE_SUCCESS));
	}
}
