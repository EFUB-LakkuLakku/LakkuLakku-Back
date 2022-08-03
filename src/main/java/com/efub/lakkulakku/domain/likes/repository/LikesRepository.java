package com.efub.lakkulakku.domain.likes.repository;

import com.efub.lakkulakku.domain.likes.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LikesRepository extends JpaRepository<Likes, UUID> {
	Optional<Likes> findById(UUID id);
	void deleteAllByDiaryId(UUID id);
}
