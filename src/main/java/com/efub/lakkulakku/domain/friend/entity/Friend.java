package com.efub.lakkulakku.domain.friend.entity;

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
public class Friend extends BaseTimeEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 16)
	private UUID id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private Users usersId;

	@OneToOne
	@JoinColumn(name = "target_id")
	private Users targetId;
	
}
