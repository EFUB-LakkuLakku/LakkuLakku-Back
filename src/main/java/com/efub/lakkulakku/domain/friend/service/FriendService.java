package com.efub.lakkulakku.domain.friend.service;

import com.efub.lakkulakku.domain.friend.dto.FriendReqDto;
import com.efub.lakkulakku.domain.friend.dto.FriendResDto;
import com.efub.lakkulakku.domain.friend.entity.Friend;
import com.efub.lakkulakku.domain.friend.exception.DuplicateFriendException;
import com.efub.lakkulakku.domain.friend.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.friend.repository.FriendRepository;
import com.efub.lakkulakku.domain.notification.entity.Notification;
import com.efub.lakkulakku.domain.notification.repository.NotificationRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FriendService {
	private final FriendRepository friendRepository;
	private final UsersRepository usersRepository;
	private final NotificationRepository notificationRepository;

	@Transactional
	public void addFriend(FriendReqDto reqDto, Users user) {
		Optional<Users> target = usersRepository.findByUid(reqDto.getUid());
		if (target.isPresent()) {
			Users targetUser = usersRepository.findByUid(reqDto.getUid()).get();
			if (isFriend(user, targetUser) != null) {
				throw new DuplicateFriendException();
			} else {
				Friend friends = Friend.builder()
						.userId(user)
						.targetId(targetUser)
						.build();
				friendRepository.save(friends);
				if(!user.getId().equals(targetUser.getId()))
				{
					toFriendNotification(user, targetUser);
					toFriendNotification(targetUser, user);
				}
			}
		} else {
			throw new UserNotFoundException();
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
			friendList.add(buildDto(one));
		}
		return friendList;
	}

	@Transactional
	public FriendResDto buildDto(Users user) {
		FriendResDto friendResDto = new FriendResDto(user);
		return friendResDto;
	}

	@Transactional
	public UUID isFriend(Users user, Users target) {
		Optional<Friend> friend = friendRepository.findByUserIdAndTargetId(user, target);
		if (friend.isPresent()) {
			return friend.get().getId();
		} else {
			friend = friendRepository.findByUserIdAndTargetId(target, user);
			if (friend.isPresent()) {
				return friend.get().getId();
			}
			return null;
		}
	}

	@Transactional
	public void deleteFriend(FriendReqDto reqDto, Users user) {
		Users delFriend = usersRepository.findByUid(reqDto.getUid())
				.orElseThrow(() -> new UserNotFoundException());
		UUID id = isFriend(user, delFriend);
		friendRepository.deleteById(id);
	}

	@Transactional
	public void toFriendNotification(Users user, Users targetUser)
	{
		Notification notification = Notification.builder()
				.userId(targetUser)
				.friendId(user)
				.notiType("친구")
				.build();
		notification.makeMessage(user, "친구", null);
		notificationRepository.save(notification);
	}
}
