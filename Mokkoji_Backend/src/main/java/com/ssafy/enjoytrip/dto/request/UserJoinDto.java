package com.ssafy.enjoytrip.dto.request;

import com.ssafy.enjoytrip.valid.PhoneNumber;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserJoinDto {

    @NotBlank
    @Size(min = 5, max = 12, message = "아이디는 5 ~ 12 글자 사이로 입력해주세요")
    private String loginId;

    @NotBlank
    @Size(min = 2, max = 12, message = "닉네임은 2 ~ 12 글자 사이로 입력해주세요")
    private String nickname;

    @NotBlank
    @Email(message = "올바른 이메일 형식으로 입력해주세요.")
    private String mail;

    @NotBlank
    @Size(min = 2, message = "올바른 이름을 입력해주세요")
    private String name;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @PhoneNumber
    @NotBlank
    private String phoneNumber;

}
