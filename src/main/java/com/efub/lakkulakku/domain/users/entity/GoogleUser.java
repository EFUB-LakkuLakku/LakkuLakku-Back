package com.efub.lakkulakku.domain.users.entity;

import com.efub.lakkulakku.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class GoogleUser extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nickname;

	@Column(nullable = false)
	private String email;

	@Column
	private String picture;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	@Builder
	public GoogleUser(String nickname, String email, String picture, Role role) {
		this.nickname = nickname;
		this.email = email;
		this.picture = picture;
		this.role = role;
	}

	public GoogleUser update(String name, String picture) {
		this.nickname = name;
		this.picture = picture;

		return this;
	}

	public String getRoleKey() {
		return this.role.getKey();
	}
}