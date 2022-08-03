package com.efub.lakkulakku.domain.comment.repository;

import com.efub.lakkulakku.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
	Optional<Comment> findById(UUID id);
	void deleteAllByDiaryId(UUID id);
	int countByDiaryId(UUID id);
}