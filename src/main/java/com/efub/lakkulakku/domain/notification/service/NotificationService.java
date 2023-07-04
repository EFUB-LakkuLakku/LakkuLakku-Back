package com.efub.lakkulakku.domain.notification.service;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.notification.dto.NotificationMapper;
import com.efub.lakkulakku.domain.notification.entity.Notification;
import com.efub.lakkulakku.domain.notification.exception.SSEConnectionException;
import com.efub.lakkulakku.domain.notification.repository.EmitterRepository;
import com.efub.lakkulakku.domain.notification.repository.NotificationRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {

	private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

	private final NotificationRepository notificationRepository;
	private final EmitterRepository emitterRepository;

	public SseEmitter subscribe(UUID memberId, String lastEventId) {

		// 유실된 데이터 여부 확인하기 위해 새로운 ID 생성
		String emitterId = makeTimeIncludeId(memberId);
		SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT));

		// 시간이 만료된 경우 자동으로 레포지토리에서 삭제 처리리
		emitter.onCompletion(() -> emitterRepository.deleteById(emitterId));
		emitter.onTimeout(() -> emitterRepository.deleteById(emitterId));

		// 503 에러를 방지하기 위한 더미 이벤트 전송
		String eventId = makeTimeIncludeId(memberId);
		sendNotification(emitter, eventId, emitterId, "EventStream Created. [userId=" + memberId + "]");

		// 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
		if (hasLostData(lastEventId)) {
			sendLostData(lastEventId, memberId, emitterId, emitter);
		}
		System.out.println("우왕 연결이당");
		return emitter;
	}

	public void send(Users receiver, String notificationType, String message) {
		Notification notification = notificationRepository.save(createNotification(receiver, notificationType, message));

		String receiverId = String.valueOf(receiver.getUserId());
		String eventId = receiverId + "_" + System.currentTimeMillis();
		Map<String, SseEmitter> emitters = emitterRepository.findAllStartWithById(receiverId);
		emitters.forEach(
				(key, emitter) -> {
					emitterRepository.saveEventCache(key, notification);
					sendNotification(emitter, eventId, key, NotificationMapper.toNotificationResDto(notification));
				}
		);
	}

	private String makeTimeIncludeId(UUID memberId) {
		return memberId + "_" + System.currentTimeMillis();
	}

	private void sendNotification(SseEmitter emitter, String eventId, String emitterId, Object data) {
		try {
			emitter.send(SseEmitter.event()
					.id(eventId)
					.data(data));
		} catch (IOException exception) {
			emitterRepository.deleteById(emitterId);
			throw new SSEConnectionException();
		}
	}

	private boolean hasLostData(String lastEventId) {
		return !lastEventId.isEmpty();
	}

	private void sendLostData(String lastEventId, UUID memberId, String emitterId, SseEmitter emitter) {
		Map<String, Object> eventCaches = emitterRepository.findAllEventCacheStartWithId(String.valueOf(memberId));
		eventCaches.entrySet().stream()
				.filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
				.forEach(entry -> sendNotification(emitter, entry.getKey(), emitterId, entry.getValue()));
	}

	private Notification createNotification(Users receiver, String notiType, String message) {
		return Notification.builder()
				.receiver(receiver)
				.notiType(notiType)
				.message(message)
				.build();
	}

	@Transactional
	public void deleteAllNotification(Users users)
	{
		List<Notification> notificationList = notificationRepository.findByUsersId(users.getUserId());
		notificationRepository.deleteAll(notificationList);
	}

}
