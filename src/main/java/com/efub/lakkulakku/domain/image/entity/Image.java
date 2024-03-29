package com.efub.lakkulakku.domain.image.entity;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.file.entity.File;
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
public class Image extends BaseTimeEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 16)
	private UUID imageId;

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
	private Integer rotation;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "file_id")
	private File file;

	@Builder
	public Image(Diary diary, Integer width, Integer height,
				 Integer x, Integer y, Integer rotation,
				 File file) {
		this.diary = diary;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.rotation = rotation;
		this.file = file;
	}
}
