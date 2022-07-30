package com.efub.lakkulakku.domain.templateResource.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class TemplateResource {

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
	public TemplateResource(String category, String url) {
		this.category = category;
		this.url = url;
	}
}
