package com.efub.lakkulakku.domain.comment.repository;

import com.efub.lakkulakku.domain.comment.entity.Comment;
import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
	Optional<Comment> findById(UUID id);

	Optional<Comment> findByUsers(Users users);

	void deleteById(UUID id);

	Optional<Comment> findByParentId(UUID parentId);

	int countByDiaryId(UUID id);

	Optional<Comment> findByDiaryAndUsers(Diary diary, Users users);

	boolean existsByDiaryAndUsers(Diary diary, Users user);
}