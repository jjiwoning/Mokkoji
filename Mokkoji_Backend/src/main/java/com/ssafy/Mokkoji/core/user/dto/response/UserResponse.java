package com.ssafy.Mokkoji.core.user.dto.response;

import com.ssafy.Mokkoji.core.user.domain.User;
import lombok.Data;

@Data
public class UserResponse {
    private Long userId;
    private String nickname;

    public UserResponse(User user) {
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
    }
}
