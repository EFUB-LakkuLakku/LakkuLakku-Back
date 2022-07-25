package com.efub.lakkulakku.domain.diary.dto;

import com.efub.lakkulakku.domain.comment.dto.CommentResDto;
import com.efub.lakkulakku.domain.image.dto.ImageResDto;
import com.efub.lakkulakku.domain.likes.dto.LikeResDto;
import com.efub.lakkulakku.domain.sticker.dto.StickerResDto;
import com.efub.lakkulakku.domain.text.dto.TextResDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryInfoResDto {

	private DiaryResDto diary;
	private List<TextResDto> textList;
	private List<ImageResDto> imageList;
	private List<StickerResDto> stickerList;
	private List<CommentResDto> commentList;
	private List<LikeResDto> likeList;

	@Builder
	public DiaryInfoResDto(DiaryResDto diary, List<TextResDto> textList, List<ImageResDto> imageList,
						   List<StickerResDto> stickerList, List<CommentResDto> commentList, List<LikeResDto> likeList) {
		this.diary = diary;
		this.textList = textList;
		this.imageList = imageList;
		this.stickerList = stickerList;
		this.commentList = commentList;
		this.likeList = likeList;
	}

}
