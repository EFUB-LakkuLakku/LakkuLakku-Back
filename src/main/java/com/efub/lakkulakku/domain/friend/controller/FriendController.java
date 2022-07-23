package com.efub.lakkulakku.domain.friend.controller;

import com.efub.lakkulakku.domain.friend.dto.FriendReqDto;
import com.efub.lakkulakku.domain.friend.dto.FriendResDto;
import com.efub.lakkulakku.domain.friend.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.friend.service.FriendService;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
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

//다시

	@PostMapping("/search")
	public ResponseEntity<?> searchFriend(@RequestBody FriendReqDto reqDto) {
		Users user = usersRepository.findByUid(reqDto.getUid())
				.orElseThrow(() -> new UserNotFoundException());
		return ResponseEntity.ok(new FriendResDto(user));

	}

	@PostMapping()
	public ResponseEntity<?> addFriend(@RequestBody FriendReqDto reqDto) {
		Users user = usersRepository.findByNickname("모래").get(); //TODO : Test 유저, 나중에 로그인 된 유저 넣기
		Optional<Users> target = usersRepository.findByUid(reqDto.getUid());
		friendService.addFriend(reqDto, user);
		return ResponseEntity.ok(FRIEND_SUCCESS);
	}

	@GetMapping()
	public List<FriendResDto> getFriends() {
		Users user = usersRepository.findByNickname("모래").get();//TODO : Test 유저, 나중에 로그인 된 유저 넣기
		return friendService.getFriends(user);
	}


	@DeleteMapping()
	public ResponseEntity<?> deleteFriend(@RequestBody FriendReqDto reqDto) {
		Users user = usersRepository.findByNickname("모래").get(); //TODO : Test 유저, 나중에 로그인 된 유저 넣기
		friendService.deleteFriend(reqDto, user);
		return ResponseEntity.ok(DELETE_FRIEND_SUCCESS);
	}


}
