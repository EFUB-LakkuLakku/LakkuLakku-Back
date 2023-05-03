package com.efub.lakkulakku.domain.users.entity;

import com.efub.lakkulakku.domain.profile.entity.Profile;
import com.efub.lakkulakku.global.entity.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;



@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 16)
	private UUID id;


	@Column(unique = true, length = 12)
	private Long uid;

	@NotNull
	private String email;

	@JsonIgnore
	@NotNull
	private String password;

	@NotNull
	private String nickname;

	@OneToOne(mappedBy = "users", cascade = CascadeType.REMOVE)
	@JoinColumn(name = "profile_id")
	private Profile profile;

	private String role;

	@Builder
	public Users(Long uid, String email, String password, String nickname) {
		this.uid = uid;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		role = "USER";
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public void setId(UUID uuid){
		this.id = uuid;
	}

	//TODO : profile 연관관계 편의 메소드

}
