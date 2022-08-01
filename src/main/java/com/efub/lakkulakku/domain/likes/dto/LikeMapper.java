package com.efub.lakkulakku.domain.likes.dto;

import com.efub.lakkulakku.domain.likes.entity.Likes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeMapper {

	public LikeResDto toLikeResDto(Likes entity) {
		if (entity == null)
			return null;

		return LikeResDto.builder()
				.id(entity.getId())
				.userId(entity.getUsers().getId())
				.createdOn(entity.getCreatedOn())
				.diaryId(entity.getDiary().getId())
				.build();
	}
}