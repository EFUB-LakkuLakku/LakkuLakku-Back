package com.efub.lakkulakku.domain.notification.entity;

import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.global.entity.BaseTimeEntity;
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
	private Users usersId;

	@OneToOne
	@JoinColumn(name = "friend_id") //알람을 발생시킨 유저
	private Users friendId;

	private String notiType; //(comment, likes, follow)

	// TODO : 알림 내용 column 필요
	private String message;

	@Builder
	public Notification(Users userId, Users friendId, String notiType, String message)
	{
		this.usersId = userId;
		this.friendId = friendId;
		this.notiType = notiType;
		this.message = friendId.getNickname()+message;
	}
}
