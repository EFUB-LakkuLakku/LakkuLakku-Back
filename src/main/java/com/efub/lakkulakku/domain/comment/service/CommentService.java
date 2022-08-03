package com.efub.lakkulakku.domain.comment.service;

import com.efub.lakkulakku.domain.comment.dto.CommentDeleteReqDto;
import com.efub.lakkulakku.domain.comment.dto.CommentReqDto;
import com.efub.lakkulakku.domain.comment.dto.CommentResDto;
import com.efub.lakkulakku.domain.comment.dto.CommentUpdateResDto;
import com.efub.lakkulakku.domain.comment.entity.Comment;
import com.efub.lakkulakku.domain.comment.exception.CommentNotFoundException;
import com.efub.lakkulakku.domain.comment.exception.ParentNotFoundException;
import com.efub.lakkulakku.domain.comment.exception.UnauthorizedException;
import com.efub.lakkulakku.domain.comment.repository.CommentRepository;
import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.likes.dto.LikeResDto;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.efub.lakkulakku.global.constant.ResponseConstant.COMMENT_ADD_SUCCESS;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final UsersRepository userRepository;
	private final DiaryRepository diaryRepository;

	@Transactional
	public CommentResDto addComment(Users user, LocalDate date, CommentReqDto commentReqDto) {

		Diary diary = diaryRepository.findById(commentReqDto.getDiaryId()).get();

		Comment comment = Comment.builder()
				.users(user)
				.content(commentReqDto.getContent())
				.diary(diary)
				.parentId(commentReqDto.getParentId())
				.isSecret(commentReqDto.isSecret())
				.build();

		commentRepository.save(comment);

		String message = COMMENT_ADD_SUCCESS;

		return CommentResDto.builder()
				.id(comment.getId())
				.userId(comment.getUsers().getId())
				//.profileImageUrl(profileImageUrl)
				.nickname(user.getNickname())
				.parentId(comment.getParentId())
				.content(comment.getContent())
				.isSecret(comment.getIsSecret())
				.createdOn(comment.getCreatedOn())
				.message(message)
				.build();

	}

	@Transactional
	public void removeComment(UUID id) {

		if(!commentRepository.existsById(id))
			throw new CommentNotFoundException();

		commentRepository.deleteById(id);

	}

	@Transactional
	public CommentUpdateResDto update(Users user, UUID id, LocalDate date, CommentReqDto commentReqDto) {

		if (!commentRepository.findById(id).get().getUsers().getId().equals(user.getId()))
			throw new UnauthorizedException();

		if(!commentRepository.existsById(id))
			throw new CommentNotFoundException();

		Comment comment = commentRepository.findById(id).orElseThrow();

		comment.update(commentReqDto.getContent());

		commentRepository.save(comment);

		return CommentUpdateResDto.builder()
				.id(comment.getId())
				.userId(comment.getUsers().getId())
				//.profileImageUrl(profileImageUrl)
				.nickname(user.getNickname())
				.parentId(comment.getParentId())
				.content(comment.getContent())
				.isSecret(comment.getIsSecret())
				.modifiedOn(comment.getModifiedOn())
				.build();

	}

	/*private Optional<Comment> findById(CommentResDto commentResDto, UUID id) {
		return commentRepository
				.findById(id);
	}

	private Optional<Comment> findByParentId(CommentResDto commentResDto, UUID parentId) {
		return commentRepository
				.findByParentId(parentId);
	}*/
}