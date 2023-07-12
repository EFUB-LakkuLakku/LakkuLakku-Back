package com.efub.lakkulakku.domain.users.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@RequiredArgsConstructor
@Repository
public class CertificationDao {
	private static final String PREFIX = "email:";
	private static final int LIMIT_TIME = 20 * 60;

	private final StringRedisTemplate stringRedisTemplate;

	public void createEmailCertification(String email, String certificationCode) {
		stringRedisTemplate.opsForValue()
				.set(PREFIX + email, certificationCode, Duration.ofSeconds(LIMIT_TIME));
	}

	public String getEmailCertification(String email) {
		return stringRedisTemplate.opsForValue().get(PREFIX + email);
	}

	public void removeEmailCertification(String email) {
		stringRedisTemplate.delete(PREFIX + email);
	}

	public boolean hasKey(String email) {
		return Boolean.TRUE.equals(stringRedisTemplate.hasKey(PREFIX + email));
	}

}
