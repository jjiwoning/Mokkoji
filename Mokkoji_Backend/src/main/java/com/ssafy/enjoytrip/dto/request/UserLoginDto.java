package com.ssafy.enjoytrip.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
	@NotBlank(message = "아이디 입력은 필수입니다.")
	String loginId;

	@NotBlank(message = "비밀번호 입력은 필수입니다.")
	String password;
}
