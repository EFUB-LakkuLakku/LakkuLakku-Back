package com.efub.lakkulakku.domain.comment.service;

import static com.efub.lakkulakku.global.constant.ResponseConstant.*;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.efub.lakkulakku.domain.diary.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.notification.entity.NotificationEnum;
import com.efub.lakkulakku.domain.users.entity.Users;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

	private final CommentRepository commentRepository;
	private final DiaryRepository diaryRepository;
	private final ApplicationEventPublisher eventPublisher;

	public CommentResDto addComment(Users user, LocalDate date, CommentReqDto commentReqDto) {
		Diary diary = diaryRepository.findById(commentReqDto.getDiaryId())
			.orElseThrow(DiaryNotFoundException::new);
		Comment comment = Comment.builder()
			.users(user)
			.content(commentReqDto.getContent())
			.diary(diary)
			.parentId(commentReqDto.getParentId())
			.isSecret(commentReqDto.isSecret())
			.build();
		commentRepository.save(comment);
		NotificationEnum type = NotificationEnum.COMMENT;
		if (commentReqDto.getParentId() != null) {
			type = NotificationEnum.RECOMMENT;
		}
		if (!user.getUserId().equals(diary.getUser().getUserId())) {
			notifyInfo(comment, type);
		}

		return CommentResDto.builder()
			.commentId(comment.getCommentId())
			.userId(comment.getUsers().getUserId())
			.profileImageUrl(checkProfileImageUrl(user))
			.nickname(user.getNickname())
			.parentId(comment.getParentId())
			.content(comment.getContent())
			.isSecret(comment.getIsSecret())
			.createdOn(comment.getCreatedOn())
			.message(COMMENT_ADD_SUCCESS)
			.build();

	}

	public void removeComment(Users user, LocalDate date, CommentDeleteReqDto commentDeleteReqDto) {

		if (!commentRepository.findById(commentDeleteReqDto.getCommentId())
			.get()
			.getUsers()
			.getUserId()
			.equals(user.getUserId())) {
			throw new UnauthorizedException();
		}

		if (!commentRepository.existsById(commentDeleteReqDto.getCommentId())) {
			throw new CommentNotFoundException();
		}

		commentRepository.deleteById(commentDeleteReqDto.getCommentId());

	}

	public CommentUpdateResDto update(Users user, LocalDate date, CommentReqDto commentReqDto) {

		Comment comment = commentRepository.findById(commentReqDto.getCommentId())
			.orElseThrow(CommentNotFoundException::new);

		if (!comment.getUsers().getUserId().equals(user.getUserId())) {
			throw new UnauthorizedException();
		}

		comment.update(commentReqDto.getContent());

		commentRepository.save(comment);

		return CommentUpdateResDto.builder()
			.commentId(comment.getCommentId())
			.userId(comment.getUsers().getUserId())
			.profileImageUrl(checkProfileImageUrl(user))
			.nickname(user.getNickname())
			.parentId(comment.getParentId())
			.content(comment.getContent())
			.isSecret(comment.getIsSecret())
			.modifiedOn(comment.getModifiedOn())
			.build();

	}

	public String checkProfileImageUrl(Users user) {
		if (user.getProfile() == null || user.getProfile().getFile() == null) {
			return null;
		} else {
			return user.getProfile().getFile().getUrl();

		}
	}

	private void notifyInfo(Comment comment, NotificationEnum notiType) {
		comment.publishEvent(eventPublisher, notiType);
	}

	public Comment findById(UUID commentId) {
		return commentRepository.findById(commentId)
			.orElseThrow(ParentNotFoundException::new);
	}
}