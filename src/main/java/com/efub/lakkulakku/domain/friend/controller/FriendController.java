package com.efub.lakkulakku.domain.friend.controller;

import com.efub.lakkulakku.domain.friend.dto.FriendReqDto;
import com.efub.lakkulakku.domain.friend.dto.FriendResDto;
import com.efub.lakkulakku.domain.friend.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.friend.service.FriendService;
import com.efub.lakkulakku.domain.profile.ProfileRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.domain.users.service.AuthUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.efub.lakkulakku.global.constant.ResponseConstant.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/friends")
@RequiredArgsConstructor
public class FriendController {
	private final FriendService friendService;
	private final UsersRepository usersRepository;


	@PostMapping("/search")
	public ResponseEntity<?> searchFriend(@RequestBody FriendReqDto reqDto) {
		Users user = usersRepository.findByUid(reqDto.getUid())
				.orElseThrow(UserNotFoundException::new);
		return ResponseEntity.ok(new FriendResDto(user));

	}

	@PostMapping()
	public ResponseEntity<?> addFriend(@AuthUsers Users user, @RequestBody FriendReqDto reqDto) {
		friendService.addFriend(reqDto, user);
		return ResponseEntity.ok(FRIEND_SUCCESS);
	}

	@GetMapping()
	public List<FriendResDto> getFriends(@AuthUsers Users user) {
		return friendService.getFriends(user);
	}


	@DeleteMapping()
	public ResponseEntity<?> deleteFriend(@AuthUsers Users user, @RequestBody FriendReqDto reqDto) {
		friendService.deleteFriend(reqDto, user);
		return ResponseEntity.ok(DELETE_FRIEND_SUCCESS);
	}

}
