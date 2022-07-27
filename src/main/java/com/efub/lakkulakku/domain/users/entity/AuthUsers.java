package com.efub.lakkulakku.domain.users.entity;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class AuthUsers extends User {

	private final Users users;

	public AuthUsers(Users users) {
		super(users.getEmail(), users.getPassword(), List.of(new SimpleGrantedAuthority(users.getRole())));
		this.users = users;
	}
}
