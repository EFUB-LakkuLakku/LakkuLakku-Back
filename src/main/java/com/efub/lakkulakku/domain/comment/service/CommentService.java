package com.efub.lakkulakku.domain.comment.service;

import com.efub.lakkulakku.domain.comment.dto.CommentResDto;
import com.efub.lakkulakku.domain.comment.dto.CommentSaveDto;
import com.efub.lakkulakku.domain.comment.dto.CommentUpdateDto;
import com.efub.lakkulakku.domain.comment.entity.Comment;
import com.efub.lakkulakku.domain.comment.exception.CommentNotFoundException;
import com.efub.lakkulakku.domain.comment.exception.ParentNotFoundException;
import com.efub.lakkulakku.domain.comment.repository.CommentRepository;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final UsersRepository usersRepository;
	//private final DiaryRepository diaryRepository;

	@Transactional
	public void commentSave(UUID id, CommentSaveDto commentSaveDto) {
		//Users users = usersRepository.findById(id);

		Comment comment = commentSaveDto.toEntity();

		commentRepository.save(comment);

	}

	@Transactional
	public void recommentSave(UUID id, UUID parentId, CommentSaveDto commentSaveDto) {
		//Users users = usersRepository.findById(id);

		Comment comment = commentSaveDto.toEntity();

		commentRepository.save(comment);

	}

	@Transactional
	public void update(UUID id, CommentUpdateDto commentUpdateDto) {

		//usersRepository.findByUserId(SecurityUtil.getLoginUserId()).orElseThrow(() -> new UnauthorizedException());

		Comment comment = commentUpdateDto.toEntity();

		commentRepository.save(comment);
	}

	@Transactional
	public void delete(UUID id) {

		Comment comment = commentRepository.findById(id)
				.orElseThrow(CommentNotFoundException::new);

		commentRepository.delete(comment);
	}

}
