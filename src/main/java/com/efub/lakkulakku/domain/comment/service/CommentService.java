package com.efub.lakkulakku.domain.comment.service;

import com.efub.lakkulakku.domain.comment.dto.CommentReqDto;
import com.efub.lakkulakku.domain.comment.dto.CommentResDto;
import com.efub.lakkulakku.domain.comment.dto.CommentUpdateResDto;
import com.efub.lakkulakku.domain.comment.entity.Comment;
import com.efub.lakkulakku.domain.comment.exception.CommentNotFoundException;
import com.efub.lakkulakku.domain.comment.exception.UnauthorizedException;
import com.efub.lakkulakku.domain.comment.repository.CommentRepository;
import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.notification.entity.Notification;
import com.efub.lakkulakku.domain.notification.repository.NotificationRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.efub.lakkulakku.global.constant.ResponseConstant.COMMENT_ADD_SUCCESS;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final DiaryRepository diaryRepository;

	private final NotificationRepository notificationRepository;

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

		String type = "댓글";
		if (commentReqDto.getParentId() != null) {
			type = "대댓글";
		}
		toCommentNotification(user, diary.getUser(), type, diary.getCreatedOn());

		return CommentResDto.builder()
				.id(comment.getId())
				.userId(comment.getUsers().getId())
				.profileImageUrl(checkProfileImageUrl(user))
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

		if (!commentRepository.existsById(id))
			throw new CommentNotFoundException();

		commentRepository.deleteById(id);

	}

	@Transactional
	public CommentUpdateResDto update(Users user, UUID id, LocalDate date, CommentReqDto commentReqDto) {

		if (!commentRepository.findById(id).get().getUsers().getId().equals(user.getId()))
			throw new UnauthorizedException();

		if (!commentRepository.existsById(id))
			throw new CommentNotFoundException();

		Comment comment = commentRepository.findById(id).orElseThrow();

		comment.update(commentReqDto.getContent());

		commentRepository.save(comment);

		return CommentUpdateResDto.builder()
				.id(comment.getId())
				.userId(comment.getUsers().getId())
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

	@Transactional
	public void toCommentNotification(Users user, Users targetUser, String type, LocalDateTime date) {
		Notification notification = Notification.builder()
				.userId(targetUser)
				.friendId(user)
				.notiType(type)
				.build();
		notification.makeMessage(user, type, date);
		notificationRepository.save(notification);
	}
}