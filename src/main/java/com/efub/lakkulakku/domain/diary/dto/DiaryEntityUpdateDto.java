package com.efub.lakkulakku.domain.diary.dto;

import com.efub.lakkulakku.domain.comment.entity.Comment;
import com.efub.lakkulakku.domain.image.entity.Image;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.sticker.entity.Sticker;
import com.efub.lakkulakku.domain.template.entity.Template;
import com.efub.lakkulakku.domain.text.entity.Text;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryEntityUpdateDto {

	private String title;
	private String titleEmoji;
	private Template template;
	private List<Comment> commentList;
	private List<Image> imageList;
	private List<Likes> likesList;
	private List<Text> textList;
	private List<Sticker> stickerList;
	private Integer cntComment;
	private Integer cntLike;

	@Builder
	public DiaryEntityUpdateDto(String title, String titleEmoji, Template template,
								List<Comment> commentList, List<Image> imageList, List<Likes> likesList,
								List<Text> textList, List<Sticker> stickerList,
								Integer cntComment, Integer cntLike) {
		this.title = title;
		this.titleEmoji = titleEmoji;
		this.template = template;
		this.commentList = commentList;
		this.imageList = imageList;
		this.likesList = likesList;
		this.textList = textList;
		this.stickerList = stickerList;
		this.cntComment = cntComment;
		this.cntLike = cntLike;
	}
}
