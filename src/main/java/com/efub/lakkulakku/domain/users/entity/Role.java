package com.efub.lakkulakku.domain.users.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Role {
	USER("ROLE_USER", "일반 사용자");

	private final String key;
	private final String title;
}
