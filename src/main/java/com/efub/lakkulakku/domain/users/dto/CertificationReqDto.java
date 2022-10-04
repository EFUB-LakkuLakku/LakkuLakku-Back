package com.efub.lakkulakku.domain.users.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CertificationReqDto {
	@NotBlank
	String certiCode;

	@NotBlank
	String email;
}
