package com.ssafy.Mokkoji.dto.response;

import com.ssafy.Mokkoji.domain.User;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long userId;
    private String nickname;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
    }
}
