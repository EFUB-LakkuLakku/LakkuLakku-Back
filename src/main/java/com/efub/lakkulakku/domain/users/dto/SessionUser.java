package com.efub.lakkulakku.domain.users.dto;

import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

	private String nickname;
	private String email;

	public SessionUser(Users user) {
		this.nickname = user.getNickname();
		this.email = user.getEmail();
	}
}
