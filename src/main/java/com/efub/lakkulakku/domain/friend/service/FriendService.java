package com.efub.lakkulakku.domain.friend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.efub.lakkulakku.domain.friend.dto.FriendReqDto;
import com.efub.lakkulakku.domain.friend.dto.FriendResDto;
import com.efub.lakkulakku.domain.friend.entity.Friend;
import com.efub.lakkulakku.domain.friend.exception.DuplicateFriendException;
import com.efub.lakkulakku.domain.friend.exception.SelfFriendException;
import com.efub.lakkulakku.domain.friend.repository.FriendRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.service.UsersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FriendService {
	private final ApplicationEventPublisher eventPublisher;
	private final FriendRepository friendRepository;
	private final UsersService usersService;

	public void addFriend(FriendReqDto reqDto, Users user) {
		if (Objects.equals(reqDto.getUid(), user.getUid())) {
			throw new SelfFriendException();
		}

		Users targetUser = usersService.findUserByUid(reqDto.getUid());
		if (checkFriendship(user, targetUser) != null) {
			throw new DuplicateFriendException();
		} else {
			Friend friend = Friend.builder()
				.sender(user)
				.target(targetUser)
				.build();
			notifyInfo(friend, "FRIEND");
			friendRepository.save(friend);
		}
	}

	public List<FriendResDto> getFriends(Users user) {
		List<Friend> friends = findAllBySenderOrTarget(user);
		List<FriendResDto> friendList = new ArrayList<>();

		for (Friend temp : friends) {
			Users friendUser = temp.getSender().getId().equals(user.getId()) ? temp.getTarget() : temp.getSender();
			friendList.add(buildDto(friendUser));
		}

		return friendList;
	}

	public FriendResDto buildDto(Users user) {
		return new FriendResDto(user);
	}

	public UUID checkFriendship(Users user, Users target) {
		UUID friendshipId = findFriendBySenderAndTarget(user, target);

		if (friendshipId == null) { //user가 sender가 아닐 경우 user가 target일때 다시 확인
			friendshipId = findFriendBySenderAndTarget(target, user);
		}

		return friendshipId;
	}

	public void deleteFriend(FriendReqDto reqDto, Users user) {
		Users delFriend = usersService.findUserByUid(reqDto.getUid());
		UUID id = checkFriendship(user, delFriend);
		friendRepository.deleteById(id);
	}

	private void notifyInfo(Friend friend, String notiType) {
		friend.publishEvent(eventPublisher, notiType);
	}

	public void deleteAllFriend(Users users) {
		List<Friend> friendUserList = friendRepository.findAllBySender(users);
		List<Friend> friendTargetList = friendRepository.findAllByTarget(users);

		friendRepository.deleteAll(friendUserList);
		friendRepository.deleteAll(friendTargetList);
	}

	@Transactional(readOnly = true)
	public UUID findFriendBySenderAndTarget(Users sender, Users target) {
		Optional<Friend> friend = friendRepository.findBySenderAndTarget(sender, target);
		return friend.map(Friend::getId).orElse(null);
	}

	@Transactional(readOnly = true)
	public List<Friend> findAllBySenderOrTarget(Users user) {
		return friendRepository.findAllBySenderOrTarget(user);
	}
}
