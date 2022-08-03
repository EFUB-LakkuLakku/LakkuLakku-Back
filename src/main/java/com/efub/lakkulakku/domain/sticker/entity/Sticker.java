package com.efub.lakkulakku.domain.sticker.entity;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.relational.core.sql.In;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Sticker extends BaseTimeEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 16)
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diary_id")
	private Diary diary;

	@Column(length = 20)
	@NotNull
	private Integer width;

	@Column(length = 20)
	@NotNull
	private Integer height;

	@Column(length = 20)
	@NotNull
	private Integer x;

	@Column(length = 20)
	@NotNull
	private Integer y;

	@Column(length = 20)
	@NotNull
	private String category;

	@Column(length = 2084)
	@NotNull
	private String url;

	@Builder
	public Sticker(Diary diary, Integer width, Integer height,
				   Integer x, Integer y, String category, String url) {
		this.diary = diary;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.category = category;
		this.url = url;
	}
}
