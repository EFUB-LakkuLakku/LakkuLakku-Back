package com.efub.lakkulakku.domain.file.entity;

import com.efub.lakkulakku.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class File extends BaseTimeEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 16)
	private UUID id;

	@NotNull
	private String filename;

	@Column(length = 36)
	@NotNull
	private String filetype;

	@NotNull
	private String url;

	@Builder
	public File(String filename, String filetype, String url)
	{
		this.filename = filename;
		this.filetype = filetype;
		this.url = url;
	}

	public void updateImage(String image){
		this.url = image;
	}

}
