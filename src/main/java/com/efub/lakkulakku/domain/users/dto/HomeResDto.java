package com.efub.lakkulakku.domain.users.dto;

import com.efub.lakkulakku.domain.diary.dto.DiaryHomeResDto;
import com.efub.lakkulakku.domain.notification.dto.NotificationHomeResDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class HomeResDto {

	private ProfileUpdateResDto user;
	private List<DiaryHomeResDto> diary;  // id, title, titleEmoji, date
	private List<NotificationHomeResDto> alarm; // id, message

	@Builder
	public HomeResDto(ProfileUpdateResDto user, List<DiaryHomeResDto> diary, List<NotificationHomeResDto> alarm) {
		this.user = user;
		this.diary = diary;
		this.alarm = alarm;
	}

}
