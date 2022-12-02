package com.efub.lakkulakku.domain.users.dto;

import com.efub.lakkulakku.domain.diary.dto.DiaryHomeMapper;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.notification.dto.NotificationMapper;
import com.efub.lakkulakku.domain.notification.repository.NotificationRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class HomeMapper {

	private final DiaryHomeMapper diaryHomeMapper;
	private final DiaryRepository diaryRepository;
	private final NotificationRepository notificationRepository;

	public HomeResDto toHomeResDto(Users entity, String year, String month) {
		if (entity == null)
			return null;

		return HomeResDto.builder()
				.user(new ProfileUpdateResDto(entity))
				.diary(diaryRepository.findUsersDiaryByYearAndMonth(entity.getId(), year, month).stream().map(diaryHomeMapper::toDiaryHomeResDto).collect(Collectors.toList()))
				.alarm(notificationRepository.findByUsersIdLimit(entity.getId()).stream().map(NotificationMapper::toNotificationResDto).collect(Collectors.toList()))
				.build();
	}
}
