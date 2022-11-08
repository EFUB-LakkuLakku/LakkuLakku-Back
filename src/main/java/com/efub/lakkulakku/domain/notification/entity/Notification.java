package com.efub.lakkulakku.domain.notification.entity;

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
public class Notification extends BaseTimeEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 16)
	private UUID id;

	@OneToOne
	@JoinColumn(name = "users_id") //알람을 받는 유저
	private Users receiver;

	private String notiType; //(comment, likes, follow)

	private String message;

	@Builder
	public Notification(Users receiver, String notiType, String message) {
		this.receiver = receiver;
		this.notiType = notiType;
		this.message = message;
	}
}
