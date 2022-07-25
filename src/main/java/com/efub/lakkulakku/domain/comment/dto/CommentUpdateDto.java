package com.efub.lakkulakku.domain.comment.dto;

import com.efub.lakkulakku.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdateDto {

	private String content;
	public Comment toEntity() {

		return Comment.builder().content(content).build();
	}

}