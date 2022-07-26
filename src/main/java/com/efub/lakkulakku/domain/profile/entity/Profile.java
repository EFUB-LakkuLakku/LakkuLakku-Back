package com.efub.lakkulakku.domain.profile.entity;

import com.efub.lakkulakku.domain.file.entity.File;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Profile extends BaseTimeEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 16)
	private UUID id;

	@OneToOne
	@JoinColumn(name = "profile_image_id")
	private File file;

	@OneToOne
	private Users users;

	private String bio;

	@Builder
	public Profile(File file, Users users, String bio)
	{
		this.file = file;
		this.users = users;
		this.bio = bio;
	}

	public void updateBio(String bio){
		this.bio = bio;
	}

	public void updateFile(File file){
		this.file = file;
	}
}
