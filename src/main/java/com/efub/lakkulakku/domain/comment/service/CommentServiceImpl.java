package com.efub.lakkulakku.domain.comment.service;

import com.efub.lakkulakku.domain.comment.dto.CommentMapper;
import com.efub.lakkulakku.domain.comment.dto.CommentResDto;
import com.efub.lakkulakku.domain.comment.entity.Comment;
import com.efub.lakkulakku.domain.comment.exception.CommentNotFoundException;
import com.efub.lakkulakku.domain.comment.repository.CommentRepository;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;

	@Override
	public void addComment(CommentResDto commentResDto) {

		Comment comment = dtoToEntity(commentResDto);

		commentRepository.save(comment);

		//return comment.getId();
	}

	@Override
	public void removeComment(UUID id) {

		Comment comment = commentRepository.findById(id)
				.orElseThrow(CommentNotFoundException::new);

		//Comment comment = dtoToEntity(commentResDto);
		commentRepository.delete(comment);

	}

	@Override
	public void update(UUID id, CommentResDto commentResDto) {

		Comment comment = dtoToEntity(commentResDto);
		//Users loggedUser = usersService.getLoggedUser();

		/*comment = Comment.builder()
				.id(commentResDto.getId())
				.content(commentResDto.getContent())
				//.users(usersRepository.getById(commentResDto.getUserId()))
				.build();*/

		commentRepository.save(comment);

	}

	/*@Override
	public void addRecomment(UUID parentId, CommentResDto commentResDto) {

		Comment comment = dtoToEntity(commentResDto);
		/*commentRepository.findById(id).ifPresent(
				comment -> recommentRepository.save(recomment.toRecommentEntity(user, comment))
		);
		commentRepository.save(comment);

		//return comment.getId();
	}*/

	/*private void checkSecretComment(UUID userId, Comment comment, CommentResDto commentResDto) {

		// 비밀댓글 체크
		if(commentResDto.getIsSecret()) {
			commentResDto.setContent("비밀 댓글입니다.");
		}
		commentResDto.setIsSecret(comment.getIsSecret());
	}*/

	/*@Override
	public Comment findById(UUID id) {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
		return new CommentResDto(comment);
	}*/
}