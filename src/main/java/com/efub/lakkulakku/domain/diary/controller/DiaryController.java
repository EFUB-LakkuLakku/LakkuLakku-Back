package com.efub.lakkulakku.domain.diary.controller;

import com.efub.lakkulakku.domain.comment.dto.CommentResDto;
import com.efub.lakkulakku.domain.diary.dto.DiaryLookupReqDto;
import com.efub.lakkulakku.domain.diary.dto.DiaryLookupResDto;
import com.efub.lakkulakku.domain.diary.dto.DiaryMessageResDto;
import com.efub.lakkulakku.domain.diary.dto.DiarySaveReqDto;
import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.diary.exception.DuplicateDiaryException;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.diary.service.DiaryService;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.domain.users.service.AuthUsers;
import com.efub.lakkulakku.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.efub.lakkulakku.global.constant.ResponseConstant.*;

@RestController
@RequestMapping("/api/v1/diaries")
@RequiredArgsConstructor
public class DiaryController {

	private final DiaryService diaryService;
	private final UsersService usersService;

	@GetMapping("/{date}")
	public ResponseEntity<DiaryLookupResDto> getDiaryByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
															@RequestParam String nickname, @AuthUsers Users loginUser) {
		Users user = usersService.findByNickname(nickname);
		diaryService.checkDiaryIsInDate(date);
		if (!diaryService.existsByDate(date))
			return ResponseEntity.ok()
					.body(new DiaryLookupResDto(null, null, null, null, null, null));

		Diary diary = diaryService.findByDateAndUser(date, user);

		// 로그인한 유저와 다이어리 작성한 유저가 같은 경우에만 조회수 증가
		if (!loginUser.getNickname().equals(nickname))
			diaryService.updateViewCount(diary);

		return ResponseEntity.ok()
				.body(diaryService.getDiaryInfo(diary));
	}

	@GetMapping("/comments/{date}")
	public List<CommentResDto> getDiaryCommentsByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
													  @RequestParam String nickname) {
		Users user = usersService.findByNickname(nickname);
		diaryService.checkDiaryIsInDate(date);

		if (!diaryService.existsByDate(date)) {
			return new ArrayList<>();
		}

		Diary diary = diaryService.findByDateAndUser(date, user);
		return diaryService.getDiaryComments(diary);
	}

	@PostMapping("/{date}")
	public ResponseEntity<DiaryMessageResDto> createDiary(@AuthUsers Users user,
														  @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		diaryService.checkDiaryIsInDate(date);
		diaryService.createDiary(user, date);
		return ResponseEntity.ok().body(new DiaryMessageResDto(date + DIARY_CREATE_SUCCESS));
	}

	@DeleteMapping("/{date}")
	public ResponseEntity<DiaryMessageResDto> deleteDiary(@AuthUsers Users user, @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		diaryService.delete(date, user);
		return ResponseEntity.ok().body(new DiaryMessageResDto(date + DIARY_DELETE_SUCCESS));
	}

	@PostMapping(value = "/save/{date}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<DiaryMessageResDto> saveDiary(@AuthUsers Users user,
														@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
														@RequestPart(value = "files", required = false) List<MultipartFile> files,
														@RequestPart(value = "key") DiaryLookupReqDto diaryLookupReqDto) throws IOException {

		DiarySaveReqDto diarySaveReqDto = DiarySaveReqDto.builder()
				.user(user)
				.multipartFileList(files)
				.diaryLookupReqDto(diaryLookupReqDto)
				.build();
		diaryService.saveDiary(date, user, diarySaveReqDto);
		return ResponseEntity.ok().body(new DiaryMessageResDto(date + DIARY_UPDATE_SUCCESS));
	}

	@PostMapping(value = "/update/{date}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<DiaryMessageResDto> updateDiary(@AuthUsers Users user,
														  @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
														  @RequestPart(value = "files", required = false) List<MultipartFile> files,
														  @RequestPart(value = "key") DiaryLookupReqDto diaryLookupReqDto) throws IOException {

		DiarySaveReqDto diarySaveReqDto = DiarySaveReqDto.builder()
				.user(user)
				.multipartFileList(files)
				.diaryLookupReqDto(diaryLookupReqDto)
				.build();
		diaryService.updateDiary(date, user, diarySaveReqDto);
		return ResponseEntity.ok().body(new DiaryMessageResDto(date + DIARY_UPDATE_SUCCESS));
	}

}
