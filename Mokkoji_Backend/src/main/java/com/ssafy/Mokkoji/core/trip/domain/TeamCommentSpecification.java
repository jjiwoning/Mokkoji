package com.ssafy.Mokkoji.core.trip.domain;

import com.ssafy.Mokkoji.core.user.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TeamCommentSpecification {

    private Long teamCommentId;

    private String content;

    private Long userId;

    private String nickname;

    private LocalDateTime createdDate;

    public TeamCommentSpecification(
            final TeamComment teamComment,
            final User user
    ) {
        this.teamCommentId = teamComment.getTeamCommentId();
        this.content = teamComment.getContent();
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.createdDate = teamComment.getCreatedDate();
    }
}
