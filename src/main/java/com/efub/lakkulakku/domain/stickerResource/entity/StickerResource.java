package com.efub.lakkulakku.domain.stickerResource.entity;

import com.efub.lakkulakku.domain.comment.entity.Comment;
import com.efub.lakkulakku.domain.image.entity.Image;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.sticker.entity.Sticker;
import com.efub.lakkulakku.domain.template.entity.Template;
import com.efub.lakkulakku.domain.text.entity.Text;
import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class StickerResource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 2084)
	@NotNull
	private String url;

	@Column(length = 20)
	@NotNull
	private String category;

	@Builder
	public StickerResource(String category, String url) {
		this.category = category;
		this.url = url;
	}
}
