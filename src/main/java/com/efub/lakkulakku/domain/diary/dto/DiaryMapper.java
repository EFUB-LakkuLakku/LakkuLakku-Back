package com.efub.lakkulakku.domain.diary.dto;

import com.efub.lakkulakku.domain.comment.dto.CommentMapper;
import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.image.dto.ImageMapper;
import com.efub.lakkulakku.domain.likes.dto.LikeMapper;
import com.efub.lakkulakku.domain.sticker.dto.StickerMapper;
import com.efub.lakkulakku.domain.text.dto.TextMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryMapper {

	private final TextMapper textMapper;
	private final ImageMapper imageMapper;
	private final StickerMapper stickerMapper;
	private final CommentMapper commentMapper;
	private final LikeMapper likeMapper;

	public DiaryInfoResDto toDiaryInfoResDto(Diary entity) {
		if (entity == null)
			return null;

		return DiaryInfoResDto.builder()
				.diary(toDiaryResDto(entity))
				.textList(entity.getTexts().stream().map(textMapper::toTextResDto).collect(Collectors.toList()))
				.imageList(entity.getImages().stream().map(imageMapper::toImageResDto).collect(Collectors.toList()))
				.stickerList(entity.getStickers().stream().map(stickerMapper::toStickerResDto).collect(Collectors.toList()))
				.commentList(entity.getComments().stream().map(commentMapper::toCommentResDto).collect(Collectors.toList()))
				.likeList(entity.getLikes().stream().map(likeMapper::toLikeResDto).collect(Collectors.toList()))
				.build();
	}

	public DiaryResDto toDiaryResDto(Diary entity) {

		if (entity == null)
			return null;

		return DiaryResDto.builder()
				.id(entity.getId())
				.userId(entity.getUser().getId())
				.date(entity.getDate())
				.title(entity.getTitle())
				.titleEmoji(entity.getTitleEmoji())
				.templateId(entity.getTemplate().getId())
				.cntComment(entity.getCntComment())
				.cntLike(entity.getCntLike())
				.build();
	}
}
