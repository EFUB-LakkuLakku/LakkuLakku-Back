package com.efub.lakkulakku.domain.comment.dto;

import com.efub.lakkulakku.domain.comment.entity.Comment;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CommentMapper {

	public CommentResDto toCommentResDto(Comment entity) {

		if (entity == null)
			return null;

		return CommentResDto.builder()
			.id(entity.getId())
			.nickname(entity.getUsers().getNickname())
			.parentId(entity.getParentId())
			.content(entity.getContent())
			.isSecret(entity.getIsSecret())
			.createdOn(entity.getCreatedOn())
			.build();
	}
}
