package com.ssafy.Mokkoji.core.user.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

	@Email
	@NotBlank
	private String mail;

	@NotBlank
	private String nickname;

	@NotBlank
	private String password;

	@NotBlank
	@Size(min = 10, max = 11)
	private String phoneNumber;

}
