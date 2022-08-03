package com.efub.lakkulakku.domain.likes.repository;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LikesRepository extends JpaRepository<Likes, UUID> {

	Optional<Likes> findById(UUID id);
	Optional<Likes> findByDiaryAndUsers(Diary diary, Users users);
	void deleteById(UUID id);
	boolean existsByDiaryAndUsers(Diary diary, Users user);

}