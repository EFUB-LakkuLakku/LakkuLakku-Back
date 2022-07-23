package com.efub.lakkulakku.domain.friend.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FriendReqDto {

	@NotBlank
	private Long uid;

}
