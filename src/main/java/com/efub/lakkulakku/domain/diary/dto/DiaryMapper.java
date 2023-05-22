package com.efub.lakkulakku.domain.diary.dto;

import com.efub.lakkulakku.domain.comment.dto.CommentMapper;
import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.image.dto.ImageMapper;
import com.efub.lakkulakku.domain.image.dto.ImageToEntityDto;
import com.efub.lakkulakku.domain.image.entity.Image;
import com.efub.lakkulakku.domain.likes.dto.LikeMapper;
import com.efub.lakkulakku.domain.sticker.dto.StickerMapper;
import com.efub.lakkulakku.domain.template.dto.TemplateMapper;
import com.efub.lakkulakku.domain.text.dto.TextMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryMapper {

	private final TemplateMapper templateMapper;
	private final TextMapper textMapper;
	private final ImageMapper imageMapper;
	private final StickerMapper stickerMapper;
	private final CommentMapper commentMapper;
	private final LikeMapper likeMapper;

	public DiaryLookupResDto toDiaryLookupResDto(Diary entity) {

		return DiaryLookupResDto.builder()
				.diary(toDiaryResDto(entity))
				.template(templateMapper.toTemplateResDto(entity.getTemplate()))
				.textList(entity.getTexts().stream().filter(Objects::nonNull).map(textMapper::toTextResDto).collect(Collectors.toList()))
				.imageList(entity.getImages().stream().filter(Objects::nonNull).map(imageMapper::toImageResDto).collect(Collectors.toList()))
				.stickerList(entity.getStickers().stream().filter(Objects::nonNull).map(stickerMapper::toStickerResDto).collect(Collectors.toList()))
				.likeList(entity.getLikes().stream().filter(Objects::nonNull).map(likeMapper::toLikeResDto).collect(Collectors.toList()))
				.build();
	}

	public DiaryResDto toDiaryResDto(Diary entity) {

		return DiaryResDto.builder()
				.id(entity.getDiaryId())
				.userId(entity.getUser().getId())
				.date(entity.getDate())
				.title(entity.getTitle())
				.titleEmoji(entity.getTitleEmoji())
				.templateId(entity.getTemplate().getId())
				.cntComment(entity.getCntComment())
				.cntLike(entity.getCntLike())
				.cntView(entity.getCntView())
				.build();
	}

	public Diary saveDiary(Diary entity, DiarySaveReqDto dto) throws IOException {
		if (dto == null)
			return null;

		DiaryLookupReqDto diaryLookupResDto = dto.getDiaryLookupReqDto();
		DiaryResDto diaryResDto = diaryLookupResDto.getDiary();

		DiaryEntityUpdateDto diaryEntityUpdateDto = DiaryEntityUpdateDto.builder()
				.title(diaryResDto.getTitle())
				.titleEmoji(diaryResDto.getTitleEmoji())
				.template(templateMapper.toEntity(entity, diaryLookupResDto.getTemplate()))
				.commentList(diaryLookupResDto.getCommentList().stream().filter(Objects::nonNull).map(commentResDto -> commentMapper.toEntity(entity, commentResDto)).collect(Collectors.toList()))
				.imageList(imageToEntity(entity, dto))
				.likesList(diaryLookupResDto.getLikeList().stream().filter(Objects::nonNull).map(likeResDto -> likeMapper.toEntity(entity, likeResDto)).collect(Collectors.toList()))
				.textList(diaryLookupResDto.getTextList().stream().filter(Objects::nonNull).map(textReqDto -> textMapper.toEntity(entity, textReqDto)).collect(Collectors.toList()))
				.stickerList(diaryLookupResDto.getStickerList().stream().filter(Objects::nonNull).map(stickerReqDto -> stickerMapper.toEntity(entity, stickerReqDto)).collect(Collectors.toList()))
				.cntComment(diaryResDto.getCntComment())
				.cntLike(diaryResDto.getCntLike())
				.build();

		entity.updateDiary(diaryEntityUpdateDto);
		return entity;
	}

	public Diary updateDiary(Diary entity, DiarySaveReqDto dto) throws IOException {
		if (dto == null)
			return null;

		DiaryLookupReqDto diaryLookupResDto = dto.getDiaryLookupReqDto();
		DiaryResDto diaryResDto = diaryLookupResDto.getDiary();

		DiaryEntityUpdateDto diaryEntityUpdateDto = DiaryEntityUpdateDto.builder()
				.title(diaryResDto.getTitle())
				.titleEmoji(diaryResDto.getTitleEmoji())
				.template(templateMapper.toEntity(entity, diaryLookupResDto.getTemplate()))
				.commentList(diaryLookupResDto.getCommentList().stream().filter(Objects::nonNull).map(commentResDto -> commentMapper.toEntity(entity, commentResDto)).collect(Collectors.toList()))
				.imageList(imageToEntity(entity, dto))
				.likesList(diaryLookupResDto.getLikeList().stream().filter(Objects::nonNull).map(likeResDto -> likeMapper.toEntity(entity, likeResDto)).collect(Collectors.toList()))
				.textList(diaryLookupResDto.getTextList().stream().filter(Objects::nonNull).map(textReqDto -> textMapper.toEntity(entity, textReqDto)).collect(Collectors.toList()))
				.stickerList(diaryLookupResDto.getStickerList().stream().filter(Objects::nonNull).map(stickerReqDto -> stickerMapper.toEntity(entity, stickerReqDto)).collect(Collectors.toList()))
				.cntComment(diaryResDto.getCntComment())
				.cntLike(diaryResDto.getCntLike())
				.build();

		entity.updateDiary(diaryEntityUpdateDto);
		return entity;
	}

	public List<Image> imageToEntity(Diary entity, DiarySaveReqDto dto) throws IOException {
		List<Image> imageList = new ArrayList<>();
		if (dto.getMultipartFileList() != null) {
			ImageToEntityDto imageToEntityDto = ImageToEntityDto.builder()
					.user(dto.getUser())
					.diary(entity)
					.multipartFileList(dto.getMultipartFileList())
					.build();
			imageList = imageMapper.toEntityList(dto.getDiaryLookupReqDto().getImageList(), imageToEntityDto);
		}
		return imageList;
	}
}