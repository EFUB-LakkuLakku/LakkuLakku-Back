package com.efub.lakkulakku.domain.notification.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Repository
@NoArgsConstructor
public class EmitterRepository {

	public final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
	private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

	public SseEmitter save(String id, SseEmitter sseEmitter) {
		emitters.put(id, sseEmitter);
		return sseEmitter;
	}

	public void saveEventCache(String id, Object event) {
		eventCache.put(id, event);
	}

	public Map<String, SseEmitter> findAllStartWithById(String id) {
		return emitters.entrySet().stream()
				.filter(entry -> entry.getKey().startsWith(id))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	public Map<String, Object> findAllEventCacheStartWithId(String id) {
		return eventCache.entrySet().stream()
				.filter(entry -> entry.getKey().startsWith(id))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	public void deleteById(String id) {
		emitters.remove(id);
	}

	public void deleteAllEmitterStartWithId(String id) {
		eventCache.forEach(
				(key, data) -> {
					if (key.startsWith(id)) {
						eventCache.remove(key);
					}
				}
		);
	}
}