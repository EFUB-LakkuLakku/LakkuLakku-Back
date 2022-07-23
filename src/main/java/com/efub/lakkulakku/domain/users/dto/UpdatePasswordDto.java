package com.efub.lakkulakku.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class UpdatePasswordDto {

    @NotBlank
    @Length(min = 2, max = 16)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!.?,])[A-Za-z\\d!.?,]{2,16}$",
            message = "16자 이내의 영문자 및 숫자와 ?,!,., , 특수문자로 입력해주세요.")
    private String beforePassword;

    @NotBlank
    @Length(min = 2, max = 16)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!.?,])[A-Za-z\\d!.?,]{2,16}$",
            message = "16자 이내의 영문자 및 숫자와 ?,!,., , 특수문자로 입력해주세요.")
    private String afterPassword;


}