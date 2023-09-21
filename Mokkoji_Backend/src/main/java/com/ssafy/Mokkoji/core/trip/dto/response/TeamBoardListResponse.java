package com.ssafy.Mokkoji.core.trip.dto.response;

import com.ssafy.Mokkoji.core.trip.domain.TeamBoard;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeamBoardListResponse {

    private Long boardId;
    private String title;
    private String nickname;
    private LocalDateTime createdDate;

    public TeamBoardListResponse(TeamBoard teamBoard) {
        this.boardId = teamBoard.getTeamBoardId();
        this.title = teamBoard.getTitle();
        this.nickname = teamBoard.getUser().getNickname();
        this.createdDate=teamBoard.getCreatedDate();
    }

}
