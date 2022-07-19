package com.efub.lakkulakku.domain.user.entity;

import com.efub.lakkulakku.domain.profile.entity.Profile;
import com.efub.lakkulakku.global.entity.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor

public class Users extends BaseTimeEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 16)
	private UUID id;


	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, length = 12)
	//@NotNull
	private Long uid;

	@NotNull
	private String email;

	@JsonIgnore
	@NotNull
	private String password;

	@NotNull
	private String nickname;

	@OneToOne(mappedBy = "users")
	@JoinColumn(name = "profile_id")
	private Profile profile;

	@Builder
	public Users(Long uid, String email, String password, String nickname)
	{
		this.uid = uid;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}
}
