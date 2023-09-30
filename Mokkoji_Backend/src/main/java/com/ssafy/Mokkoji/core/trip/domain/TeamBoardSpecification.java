package com.ssafy.Mokkoji.core.trip.domain;

import com.ssafy.Mokkoji.core.user.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TeamBoardSpecification {

    private Long teamBoardId;
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime createdDate;

    public TeamBoardSpecification(
            final TeamBoard teamBoard,
            final User user
    ) {
        this.teamBoardId = teamBoard.getTeamBoardId();
        this.title = teamBoard.getTitle();
        this.content = teamBoard.getContent();
        this.nickname = user.getNickname();
        this.createdDate = teamBoard.getCreatedDate();
    }
}
