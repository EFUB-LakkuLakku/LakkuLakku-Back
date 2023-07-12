package com.efub.lakkulakku.domain.users.dto;

import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupReqDto {
	@NotBlank
	@Email(message = "이메일 형식이 아닙니다.")
	private String email;
	@NotBlank
	@Length(min = 2, max = 16)
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!.?,])[A-Za-z\\d!.?,]{2,16}$",
			message = "16자 이내의 영문자 및 숫자와 ?,!,., , 특수문자로 입력해주세요.")
	private String password;
	@NotBlank
	private String nickname;

	@Builder
	public SignupReqDto(String email, String password, String nickname) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}

	public Users toEntity() {
		return Users.builder()
				.uid(System.currentTimeMillis())
				.email(email)
				.password(password)
				.nickname(nickname)
				.build();
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
