package com.efub.lakkulakku.domain.users.controller;


import com.efub.lakkulakku.domain.diary.dto.DiaryHomeResDto;
import com.efub.lakkulakku.domain.notification.dto.NotificationResDto;
import com.efub.lakkulakku.domain.users.dto.ProfileUpdateResDto;
import com.efub.lakkulakku.domain.users.dto.UserGetReqDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.domain.users.service.AuthUsers;
import com.efub.lakkulakku.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
public class HomeController {

	private final UsersRepository usersRepository;
	private final UsersService usersService;

	@GetMapping({"/diary/{year}/{month}", "/diary"})
	public List<DiaryHomeResDto> getHomeDiaryByDate(@PathVariable(value = "year", required = false) String year, @PathVariable(value = "month", required = false) String month, @Valid @RequestBody UserGetReqDto reqDto) {
		//TODO : LocalDateTime의 범위를 넘어가는 경우 에러 발생
//		if (Integer.parseInt(year) <= 1969 || Integer.parseInt(year) >= 2100)
//			throw new BadDateRequestException();
		Users user = usersRepository.findByNickname(reqDto.getNickname())
				.orElseThrow(UserNotFoundException::new);

		return usersService.getHomeDiary(user, year, month);
	}

	@GetMapping("/user")
	public ResponseEntity<ProfileUpdateResDto> getHomeUser(@Valid @RequestBody UserGetReqDto reqDto) {
		Users user = usersRepository.findByNickname(reqDto.getNickname())
				.orElseThrow(UserNotFoundException::new);

		return ResponseEntity.ok()
				.body(new ProfileUpdateResDto(user));
	}

	@GetMapping("/notification")
	public List<NotificationResDto> getHomeNotification(@AuthUsers Users user) {
		return usersService.findAllNotifications(user);
	}
}
