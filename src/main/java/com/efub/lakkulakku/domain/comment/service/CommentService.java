package com.efub.lakkulakku.domain.comment.service;

import com.efub.lakkulakku.domain.comment.dto.CommentResDto;
import com.efub.lakkulakku.domain.comment.entity.Comment;

import java.util.UUID;

import com.efub.lakkulakku.domain.comment.repository.CommentRepository;
import com.efub.lakkulakku.domain.diary.entity.Diary;

import java.util.UUID;

public interface CommentService {

	void addComment(CommentResDto commentResDto);

	void removeComment(UUID id);
	//void update(UUID id, CommentResDto commentResDto);

	//void addRecomment(UUID parentId, CommentResDto commentResDto);
	//void removeRecomment(UUID parentId);
	//void editRecomment() ;

	//Comment findById(UUID id) throws Exception;

	default Comment dtoToEntity(CommentResDto commentResDto) {

		//Diary diary = Diary.builder().id(commentResDto.getId()).build();

		Comment comment = Comment.builder()
				.id(commentResDto.getId())
				.content(commentResDto.getContent())
				//.userId(commentResDto.getUserId())
				//.diary(diary)
				.build();

		return comment;
	}

	default CommentResDto entityToDTO(Comment comment) {

		CommentResDto commentResDto = CommentResDto.builder()
				.id(comment.getId())
				.content(comment.getContent())
				//.users(comment.getUsers())
				.build();

		return commentResDto;
	}

}

