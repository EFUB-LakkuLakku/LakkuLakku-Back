package com.efub.lakkulakku.domain.likes.repository;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LikesRepository extends JpaRepository<Likes, UUID> {
	Optional<Likes> findById(UUID id);

	Optional<Likes> findByDiaryAndUsers(Diary diary, Users users);

	void deleteById(UUID id);

	boolean existsByDiaryAndUsers(Diary diary, Users user);

	int countByDiaryId(UUID id);
}
