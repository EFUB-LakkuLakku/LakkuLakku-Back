package com.efub.lakkulakku.domain.diary.entity;

import com.efub.lakkulakku.domain.comment.entity.Comment;
//import com.efub.lakkulakku.domain.diary.dto.DiaryResDto;
import com.efub.lakkulakku.domain.image.entity.Image;
import com.efub.lakkulakku.domain.like.entity.Like;
import com.efub.lakkulakku.domain.sticker.entity.Sticker;
import com.efub.lakkulakku.domain.template.entity.Template;
import com.efub.lakkulakku.domain.text.entity.Text;
import com.efub.lakkulakku.domain.user.entity.User;
import com.efub.lakkulakku.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
	@JoinColumn(name = "user_id")
	private User user;

	@Column(length = 10)
	@NotNull
	private String date;

	@Column(length = 50)
	@NotNull
	private String title;

	@Column(length = 5)
	private String titleEmoji;

	@OneToMany(mappedBy = "diary")
	private List<Comment> comments = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "template_id")
	private Template template;

	@OneToMany(mappedBy = "diary")
	private List<Image> images = new ArrayList<>();

	@OneToMany(mappedBy = "diary")
	private List<Like> likes = new ArrayList<>();

	@OneToMany(mappedBy = "diary")
	private List<Text> texts = new ArrayList<>();

	@OneToMany(mappedBy = "diary")
	private List<Sticker> stickers = new ArrayList<>();

	@Column(length = 10)
	private Integer cntComment;

	@Column(length = 10)
	private Integer cntLike;

	@PrePersist
	public void prePersist() {
		this.cntComment = this.cntComment == null ? 0 : this.cntComment;
		this.cntLike = this.cntLike == null ? 0 : this.cntLike;
	}

	@Builder
	public Diary(User user, String date) {
		this.user = user;
		this.date = date;
	}
}