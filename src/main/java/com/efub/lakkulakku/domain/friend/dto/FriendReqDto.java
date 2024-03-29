package com.efub.lakkulakku.domain.friend.dto;

import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FriendReqDto {

	@NotBlank
	private Long uid;

	@Builder
	public FriendReqDto(Long uid) {
		this.uid = uid;
	}
}