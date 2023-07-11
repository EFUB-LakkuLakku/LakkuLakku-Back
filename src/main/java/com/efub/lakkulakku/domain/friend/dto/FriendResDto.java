package com.efub.lakkulakku.domain.friend.dto;

import java.util.Optional;

import com.efub.lakkulakku.domain.file.entity.File;
import com.efub.lakkulakku.domain.profile.entity.Profile;
import com.efub.lakkulakku.domain.users.entity.Users;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reactor.util.annotation.Nullable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FriendResDto {
	private Long uid;

	@Nullable
	private String profileImageUrl;
	private String nickname;

	@Builder
	public FriendResDto(Users user) {
		this.uid = user.getUid();
		this.profileImageUrl = Optional.ofNullable(user.getProfile())
			.map(Profile::getFile)
			.map(File::getUrl)
			.orElse(null);
		this.nickname = user.getNickname();
	}

}

