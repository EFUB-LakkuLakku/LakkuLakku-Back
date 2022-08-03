package com.efub.lakkulakku.domain.diary.entity;

import com.efub.lakkulakku.domain.comment.entity.Comment;
import com.efub.lakkulakku.domain.diary.dto.DiaryEntityUpdateDto;
import com.efub.lakkulakku.domain.image.entity.Image;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.sticker.entity.Sticker;
import com.efub.lakkulakku.domain.template.entity.Template;
import com.efub.lakkulakku.domain.text.entity.Text;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Diary extends BaseTimeEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 16)
	private UUID id;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "users_id")
	private Users user;

	@Column(length = 10)
	@NotNull
	private LocalDate date;

	@Column(length = 50)
	@NotNull
	private String title;

	@Column(length = 5)
	private String titleEmoji;

	@OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Comment> comments = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id")
	private Template template;

	@OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Image> images = new ArrayList<>();

	@OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Likes> likes = new ArrayList<>();

	@OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Text> texts = new ArrayList<>();

	@OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Sticker> stickers = new ArrayList<>();

	@Column(length = 10, columnDefinition = "bigint(10) default 0")//
	private Integer cntComment;

	@Column(length = 10, columnDefinition = "bigint(10) default 0")//
	private Integer cntLike;

	@PrePersist
	public void prePersist() {
		this.title = "제목을 입력해주세요";
		this.titleEmoji = "📒";
		this.cntComment = 0;
		this.cntLike = 0;
	}

	@Builder
	public Diary(Users user, LocalDate date, List<Comment> comments,
				 List<Image> images, List<Likes> likes, List<Text> texts, List<Sticker> stickers) {
		this.user = user;
		this.date = date;
		this.comments = comments;
		this.images = images;
		this.likes = likes;
		this.texts = texts;
		this.stickers = stickers;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public void updateDiary(DiaryEntityUpdateDto dto) {
		this.title = dto.getTitle();
		this.titleEmoji = dto.getTitleEmoji();
		this.comments = dto.getCommentList();
		this.template = dto.getTemplate();
		this.images = dto.getImageList();
		this.likes = dto.getLikesList();
		this.texts = dto.getTextList();
		this.stickers = dto.getStickerList();
		this.cntComment = dto.getCntComment();
		this.cntLike = dto.getCntLike();
	}
}