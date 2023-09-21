package com.ssafy.Mokkoji.core.trip.dto.response;

import com.ssafy.Mokkoji.core.trip.domain.TeamBoard;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeamBoardDetailResponse {

    private Long teamBoardId;
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime createdDate;

    public TeamBoardDetailResponse(TeamBoard teamBoard) {
        this.teamBoardId = teamBoard.getTeamBoardId();
        this.title = teamBoard.getTitle();
        this.content = teamBoard.getContent();
        this.nickname = teamBoard.getUser().getNickname();
        this.createdDate = teamBoard.getCreatedDate();
    }

}
