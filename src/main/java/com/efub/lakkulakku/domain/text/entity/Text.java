package com.efub.lakkulakku.domain.text.entity;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Text extends BaseTimeEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 16)
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diary_id")
	private Diary diary;

	@Column(length = 50)
	@NotNull
	private String style;

	@Column(length = 20)
	@NotNull
	private Integer weight;

	@Column(length = 20)
	@NotNull
	private Integer size;

	@Column(length = 20)
	@NotNull
	private Integer width;

	@Column(length = 20)
	@NotNull
	private Integer height;

	@Column(length = 20)
	@NotNull
	private String align;

	@Column(length = 20)
	@NotNull
	private String color;

	@NotNull
	private String content;

	@Column(length = 20)
	@NotNull
	private Integer x;

	@Column(length = 20)
	@NotNull
	private Integer y;

	@Column(length = 20)
	@NotNull
	private Integer rotation;

	@Builder
	public Text(Diary diary, String style, Integer weight, Integer size,
				Integer width, Integer height, String align, String color, String content,
				Integer x, Integer y, Integer rotation) {
		this.diary = diary;
		this.style = style;
		this.weight = weight;
		this.size = size;
		this.width = width;
		this.height = height;
		this.align = align;
		this.color = color;
		this.content = content;
		this.x = x;
		this.y = y;
		this.rotation = rotation;
	}
}
