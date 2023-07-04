package com.efub.lakkulakku.domain.friend.controller;

import static com.efub.lakkulakku.global.constant.ResponseConstant.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efub.lakkulakku.domain.friend.dto.FriendReqDto;
import com.efub.lakkulakku.domain.friend.dto.FriendResDto;
import com.efub.lakkulakku.domain.friend.service.FriendService;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.service.AuthUsers;
import com.efub.lakkulakku.domain.users.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/friends")
@RequiredArgsConstructor
public class FriendController {
	private final FriendService friendService;
	private final UsersService usersService;

	@PostMapping("/search")
	public ResponseEntity<FriendResDto> searchFriend(@RequestBody FriendReqDto reqDto) {
		Users user = usersService.findUserByUid(reqDto.getUid());
		return ResponseEntity.ok(new FriendResDto(user));

	}

	@PostMapping
	public ResponseEntity<String> addFriend(@AuthUsers Users user, @RequestBody FriendReqDto reqDto) {
		friendService.addFriend(reqDto, user);
		return ResponseEntity.ok(FRIEND_SUCCESS);
	}

	@GetMapping
	public List<FriendResDto> getFriends(@AuthUsers Users user) {
		return friendService.getFriends(user);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteFriend(@AuthUsers Users user, @RequestBody FriendReqDto reqDto) {
		friendService.deleteFriend(reqDto, user);
		return ResponseEntity.ok(DELETE_FRIEND_SUCCESS);
	}

}
