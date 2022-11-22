package com.efub.lakkulakku.domain.users.controller;

import com.efub.lakkulakku.domain.diary.service.DiaryService;
import com.efub.lakkulakku.domain.friend.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.friend.service.FriendService;
import com.efub.lakkulakku.domain.users.dto.WithdrawReqDto;
import com.efub.lakkulakku.domain.users.dto.WithdrawResDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.efub.lakkulakku.global.constant.ResponseConstant.WITHDRAW_SUCCESS;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class WithdrawalController {

	private final UsersRepository usersRepository;
	private final UsersService usersService;
	private final DiaryService diaryService;
	private final FriendService friendService;

	@DeleteMapping("/withdrawal")
	public ResponseEntity<WithdrawResDto> withdrawal(@Valid @RequestBody WithdrawReqDto withdrawReqDto) {
		Users users = usersRepository.findByNickname(withdrawReqDto.getNickname())
				.orElseThrow(UserNotFoundException::new);

		diaryService.deleteAllDiary(users);
		friendService.deleteAllFriend(users);
		usersRepository.delete(users);

		WithdrawResDto dto = WithdrawResDto.builder()
				.nickname(withdrawReqDto.getNickname())
				.message(WITHDRAW_SUCCESS)
				.build();
		return ResponseEntity.ok(dto);
	}
}
