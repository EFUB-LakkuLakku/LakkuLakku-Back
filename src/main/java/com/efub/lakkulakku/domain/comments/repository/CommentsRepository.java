package com.efub.lakkulakku.domain.comments.repository;

import com.efub.lakkulakku.domain.comments.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CommentsRepository extends JpaRepository<Comment, UUID> {

}