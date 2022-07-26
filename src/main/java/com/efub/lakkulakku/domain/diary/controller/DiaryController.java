package com.efub.lakkulakku.domain.diary.controller;

import com.efub.lakkulakku.domain.diary.dto.DiaryInfoResDto;
import com.efub.lakkulakku.domain.diary.dto.DiaryMessageResDto;
import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.diary.exception.DuplicateDiaryException;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/diaries/{date}")
@RequiredArgsConstructor
public class DiaryController {

	private final DiaryService diaryService;
	private final DiaryRepository diaryRepository;

	@GetMapping
	public ResponseEntity<DiaryInfoResDto> getDiaryByDate(@Valid @PathVariable("date") String date) {
		// TODO : LocalDateTime의 범위를 넘어가는 경우 에러 발생
		// TODO : date 형식 정하기 -> 형식 이상할 경우 에러(400번대)

		if (!diaryRepository.existsByDate(date))
			return ResponseEntity.ok()
					.body(new DiaryInfoResDto(null, null, null, null, null, null));

		Diary diary = diaryRepository.findByDate(date).orElseThrow(DiaryNotFoundException::new);
		return ResponseEntity.ok()
				.body(diaryService.getDiaryInfo(diary));
	}

	@PostMapping
	public ResponseEntity<DiaryMessageResDto> createDiary(@Valid @PathVariable("date") String date) {
		if (diaryRepository.existsByDate(date))
			throw new DuplicateDiaryException();
		diaryService.createDiary(date);
		return ResponseEntity.ok().body(new DiaryMessageResDto(date + " 날짜의 다이어리가 생성되었습니다."));
	}

	@DeleteMapping
	public ResponseEntity<DiaryMessageResDto> deleteDiary(@Valid @PathVariable("date") String date) {
		diaryService.deleteDiary(date);
		return ResponseEntity.ok().body(new DiaryMessageResDto(date + " 날짜의 다이어리가 삭제되었습니다."));
	}

//	@PostMapping("/save")
//	public ResponseEntity<DiaryMessageResDto> updateDiary(@Valid @PathVariable("date") String date, @RequestBody DiaryInfoResDto diaryInfoResDto) {
//		diaryService.updateDiary(date, diaryInfoResDto);
//		return ResponseEntity.ok().body(new DiaryMessageResDto(date + " 날짜의 다이어리가 성공적으로 저장되었습니다."));
//	}
}
