package com.efub.lakkulakku.domain.comment.dto;

import org.springframework.stereotype.Component;

import com.efub.lakkulakku.domain.comment.entity.Comment;
import com.efub.lakkulakku.domain.comment.exception.CommentNotFoundException;
import com.efub.lakkulakku.domain.comment.repository.CommentRepository;
import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommentMapper {

	private final UsersRepository usersRepository;
	private final CommentRepository commentRepository;

	public CommentResDto toCommentResDto(Comment entity) {
		String profileImageUrl;
		if (entity == null) {
			return null;
		}

		if (entity.getUsers().getProfile() == null || entity.getUsers().getProfile().getFile() == null) {
			profileImageUrl = null;
		} else {
			profileImageUrl = entity.getUsers().getProfile().getFile().getUrl();
		}

		return CommentResDto.builder()
			.commentId(entity.getCommentId())
			.userId(entity.getUsers().getUserId())
			.profileImageUrl(profileImageUrl)
			.nickname(entity.getUsers().getNickname())
			.parentId(entity.getParentId())
			.content(entity.getContent())
			.isSecret(entity.getIsSecret())
			.createdOn(entity.getCreatedOn())
			.build();
	}

	public Comment checkIsEntity(Diary diary, CommentResDto dto) {
		Comment comment;

		if (dto.getCommentId() == null) {
			comment = toEntity(diary, dto);
		} else {
			comment = commentRepository.findById(dto.getCommentId()).orElseThrow(CommentNotFoundException::new);
		}
		return comment;
	}

	public Comment toEntity(Diary diary, CommentResDto dto) {
		if (diary == null || dto == null) {
			return null;
		}
		Users users = usersRepository.findById(dto.getUserId()).orElseThrow(UserNotFoundException::new);

		return Comment.builder()
			.diary(diary)
			.users(users)
			.content(dto.getContent())
			.parentId(dto.getParentId())
			.isSecret(dto.getIsSecret())
			.build();
	}
}
