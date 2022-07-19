package com.efub.lakkulakku.domain.profile.entity;

import com.efub.lakkulakku.domain.file.entity.File;
import com.efub.lakkulakku.domain.user.entity.Users;
import com.efub.lakkulakku.global.entity.BaseTimeEntity;
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
}
