package com.ssafy.enjoytrip.dto.response;

import com.ssafy.enjoytrip.domain.User;
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
