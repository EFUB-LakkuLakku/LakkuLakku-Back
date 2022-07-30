package com.efub.lakkulakku.domain.template.entity;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.global.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Template extends BaseTimeEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 16)
	private UUID id;

	@OneToOne(mappedBy = "template")
	private Diary diary;

	@Column(length = 2084)
	@NotNull
	private String url;

	@Column(length = 20)
	@NotNull
	private String category;
}
