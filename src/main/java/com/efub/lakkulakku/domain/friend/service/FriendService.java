package com.efub.lakkulakku.domain.friend.service;

import com.efub.lakkulakku.domain.friend.dto.FriendReqDto;
import com.efub.lakkulakku.domain.friend.dto.FriendResDto;
import com.efub.lakkulakku.domain.friend.entity.Friend;
import com.efub.lakkulakku.domain.friend.exception.DuplicateFriendException;
import com.efub.lakkulakku.domain.friend.exception.SelfFriendException;
import com.efub.lakkulakku.domain.friend.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.friend.repository.FriendRepository;
import com.efub.lakkulakku.domain.notification.entity.Notification;
import com.efub.lakkulakku.domain.notification.repository.NotificationRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FriendService {
	private final ApplicationEventPublisher eventPublisher;
	private final FriendRepository friendRepository;
	private final UsersRepository usersRepository;
	private final NotificationRepository notificationRepository;

	@Transactional
	public void addFriend(FriendReqDto reqDto, Users user) {
		if (Objects.equals(reqDto.getUid(), user.getUid())) {
			throw new SelfFriendException();
		}

		Users targetUser = usersRepository.findByUid(reqDto.getUid()).orElseThrow(UserNotFoundException::new);
		if (isFriend(user, targetUser) != null) {
			throw new DuplicateFriendException();
		} else {
			Friend friend = Friend.builder()
					.userId(user)
					.targetId(targetUser)
					.build();
			notifyInfo(friend, "FRIEND");
			friendRepository.save(friend);
		}
	}

	@Transactional
	public List<FriendResDto> getFriends(Users user) {
		List<Friend> friends1 = friendRepository.findAllByUserId(user);
		List<Friend> friends2 = friendRepository.findAllByTargetId(user);
		List<FriendResDto> friendList = new ArrayList<>();
		for (Friend temp : friends1) {
			Users one = temp.getTargetId();
			friendList.add(buildDto(one));
		}
		for (Friend temp : friends2) {
			Users one = temp.getUserId();
			if (one.equals(user)) {
				continue;
			}
			else
			{
				friendList.add(buildDto(one));
			}
		}
		return friendList;
	}

	@Transactional
	public FriendResDto buildDto(Users user) {
		return new FriendResDto(user);
	}

	@Transactional
	public UUID isFriend(Users user, Users target) {
		Optional<Friend> friend = friendRepository.findByUserIdAndTargetId(user, target);
		if (friend.isPresent()) {
			return friend.get().getId();
		} else {
			friend = friendRepository.findByUserIdAndTargetId(target, user);
			return friend.map(Friend::getId).orElse(null);
		}
	}

	@Transactional
	public void deleteFriend(FriendReqDto reqDto, Users user) {
		Users delFriend = usersRepository.findByUid(reqDto.getUid())
				.orElseThrow(UserNotFoundException::new);
		UUID id = isFriend(user, delFriend);
		friendRepository.deleteById(id);
	}

	private void notifyInfo(Friend friend, String notiType) {
		friend.publishEvent(eventPublisher, notiType);
	}

	@Transactional
	public void deleteAllFriend(Users users){
		List<Friend> friendUserList = friendRepository.findAllByUserId(users);
		List<Friend> friendTargetList = friendRepository.findAllByTargetId(users);
		friendRepository.deleteAll(friendUserList);
		friendRepository.deleteAll(friendTargetList);
	}
}
