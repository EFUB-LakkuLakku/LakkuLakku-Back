package com.efub.lakkulakku.domain.diary.dto;

import com.efub.lakkulakku.domain.comment.dto.CommentResDto;
import com.efub.lakkulakku.domain.image.dto.ImageReqDto;
import com.efub.lakkulakku.domain.likes.dto.LikeResDto;
import com.efub.lakkulakku.domain.sticker.dto.StickerReqDto;
import com.efub.lakkulakku.domain.template.dto.TemplateResDto;
import com.efub.lakkulakku.domain.text.dto.TextReqDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryLookupReqDto {

	private DiaryResDto diary;
	private TemplateResDto template;
	private List<TextReqDto> textList;
	private List<ImageReqDto> imageList;
	private List<StickerReqDto> stickerList;
	private List<CommentResDto> commentList;
	private List<LikeResDto> likeList;

	@Builder
	public DiaryLookupReqDto(DiaryResDto diary, TemplateResDto template,
							 List<TextReqDto> textList, List<ImageReqDto> imageList, List<StickerReqDto> stickerList,
							 List<CommentResDto> commentList, List<LikeResDto> likeList) {
		this.diary = diary;
		this.template = template;
		this.textList = textList;
		this.imageList = imageList;
		this.stickerList = stickerList;
		this.commentList = commentList;
		this.likeList = likeList;
	}
}
