package com.efub.lakkulakku.domain.comment.service;

import com.efub.lakkulakku.domain.comment.dto.CommentResDto;
import com.efub.lakkulakku.domain.comment.dto.CommentUpdateResDto;
import com.efub.lakkulakku.domain.comment.entity.Comment;
import com.efub.lakkulakku.domain.comment.exception.CommentNotFoundException;
import com.efub.lakkulakku.domain.comment.exception.UnauthorizedException;
import com.efub.lakkulakku.domain.comment.repository.CommentRepository;
import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final UsersRepository userRepository;
	private final DiaryRepository diaryRepository;

	@Transactional
	public void addComment(CommentResDto commentResDto) {
		Users user = userRepository.findByEmail("seojunng@gmail.com").get();
		Diary diary = diaryRepository.findByDate(LocalDate.parse("2022-08-22")).get();

		Comment comment = Comment.builder()
				.id(commentResDto.getId())
				.parentId(commentResDto.getParentId())
				.content(commentResDto.getContent())
				.users(user)
				//.diary(diary)
				.isSecret(commentResDto.getIsSecret())
				.build();

		/*commentRepository.findById(id).ifPresent(
				comment -> commentRepository.save(comment)
		);*/

		commentRepository.save(comment);

	}

	@Transactional
	public void removeComment(UUID id/*, UUID parentId*/) {

		Comment comment = commentRepository.findById(id)
				.orElseThrow(CommentNotFoundException::new);

		commentRepository.deleteById(id);

	}

	@Transactional
	public CommentUpdateResDto update(UUID id, CommentResDto commentResDto) {

		Comment comment = commentRepository.findById(id) //댓글 작성한 유저 불러오기로 변경
				.orElseThrow(UnauthorizedException::new);

		//if (!comment.getUsers().equals(loggedUser))

		comment.update(commentResDto.getContent());

		commentRepository.save(comment);

		return new CommentUpdateResDto(comment.getId(), comment.getParentId(), comment.getContent(),
				comment.getIsSecret(),comment.getCreatedOn());

	}

	@Transactional
	public void addRecomment(UUID id, UUID parentId, CommentResDto commentResDto) {

		Users user = userRepository.findByEmail("seojunng@gmail.com").get();

		Comment recomment = Comment.builder()
				.id(commentResDto.getId())
				.parentId(commentResDto.getParentId())
				.content(commentResDto.getContent())
				.users(user)
				//.diary(diary)
				.isSecret(commentResDto.getIsSecret())
				.build();

		//Comment recomment = Comment.addRecomment(diary, commentResDto.getContent(), users, parent);

		commentRepository.findByParentId(parentId).ifPresent(
				comment -> commentRepository.save(recomment));

		//commentRepository.save(recomment);
	}

	private Optional<Comment> findById(CommentResDto commentResDto, UUID id) {
		return commentRepository
				.findById(id);
	}

	private Optional<Comment> findByParentId(CommentResDto commentResDto, UUID parentId) {
		return commentRepository
				.findByParentId(parentId);
	}
}