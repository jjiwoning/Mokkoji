package com.ssafy.Mokkoji.core.user.dto.request;

import com.ssafy.Mokkoji.core.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    private Long userId;

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

    public User toEntity() {
        return User.builder().userId(userId).mail(mail).nickname(nickname).password(password).phoneNumber(phoneNumber).build();
    }

}
