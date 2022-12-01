package com.efub.lakkulakku.domain.notification.repository;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.notification.entity.Notification;
import com.efub.lakkulakku.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

	@Query(value = "SELECT * FROM notification n " +
					"WHERE n.users_id=:usersId " +
					"ORDER BY n.created_on desc LIMIT 5", nativeQuery = true)
	List<Notification> findByUsersId(@Param("usersId") UUID usersId);
}
