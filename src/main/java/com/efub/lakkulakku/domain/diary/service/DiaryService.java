package com.efub.lakkulakku.domain.diary.service;

import com.efub.lakkulakku.domain.comment.dto.CommentMapper;
import com.efub.lakkulakku.domain.comment.dto.CommentResDto;
import com.efub.lakkulakku.domain.comment.repository.CommentRepository;
import com.efub.lakkulakku.domain.diary.dto.DiaryLookupResDto;
import com.efub.lakkulakku.domain.diary.dto.DiaryMapper;
import com.efub.lakkulakku.domain.diary.dto.DiarySaveReqDto;
import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.exception.BadDateRequestException;
import com.efub.lakkulakku.domain.diary.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.likes.repository.LikesRepository;
import com.efub.lakkulakku.domain.template.entity.Template;
import com.efub.lakkulakku.domain.template.repository.TemplateRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DiaryService {

	private final DiaryRepository diaryRepository;
	private final TemplateRepository templateRepository;
	private final CommentRepository commentRepository;
	private final LikesRepository likesRepository;
	private final DiaryMapper diaryMapper;
	private final CommentMapper commentMapper;

	public void checkDiaryIsInDate(LocalDate date) {
		LocalDate END_DATE = LocalDate.of(2099, 12, 31);
		LocalDate START_DATE = LocalDate.of(1970, 1, 1);

		if (date.isBefore(START_DATE) || date.isAfter(END_DATE))
			throw new BadDateRequestException();
	}

	public Diary updateDiaryCntCommentAndCntLikes(Diary diary) {
		int cntComment = commentRepository.countByDiaryId(diary.getId());
		//int cntLike = likesRepository.countByDiaryId(diary.getId());
		diary.setCntComment(cntComment);
		//diary.setCntLike(cntLike);
		return diary;
	}

	public DiaryLookupResDto getDiaryInfo(Diary diary) {
		diary = updateDiaryCntCommentAndCntLikes(diary);
		return diaryMapper.toDiaryLookupResDto(diary);
	}

	public List<CommentResDto> getDiaryComments(Diary diary) {
		return diary.getComments().stream().filter(Objects::nonNull).map(commentMapper::toCommentResDto).collect(Collectors.toList());
	}

	@Transactional
	public void createDiary(Users user, LocalDate diaryDate) {
		Diary diary = Diary.builder()
				.user(user)
				.date(diaryDate)
				.comments(null)
				.images(null)
				.likes(null)
				.texts(null)
				.stickers(null)
				.build();
		diaryRepository.save(diary);

		Template template = Template.builder()
				.diary(diary)
				.url("https://s3.ap-northeast-2.amazonaws.com/lakku-lakku.com/template/basic/%E1%84%86%E1%85%A9%E1%84%83%E1%85%A5%E1%86%AB_1.jpg")
				.category("basic")
				.build();
		diary.setTemplate(template);
		templateRepository.save(template);
	}

	@Transactional
	public void saveDiary(LocalDate date, DiarySaveReqDto dto) throws IOException {
		Diary diary = diaryRepository.findByDate(date)
				.orElseThrow(DiaryNotFoundException::new);
		Diary savedDiary = diaryMapper.saveDiary(diary, dto);
		diaryRepository.save(savedDiary);
	}

	@Transactional
	public void updateDiary(LocalDate date, DiarySaveReqDto dto) throws IOException {
		Diary diary = diaryRepository.findByDate(date)
				.orElseThrow(DiaryNotFoundException::new);
		Diary updatedDiary = diaryMapper.updateDiary(diary, dto);
		diaryRepository.save(updatedDiary);
	}

	@Transactional
	public void deleteAllDiary(Users users){
		List<Diary> diaryList = diaryRepository.findByUser(users);
		diaryRepository.deleteAll(diaryList);
	}
}
