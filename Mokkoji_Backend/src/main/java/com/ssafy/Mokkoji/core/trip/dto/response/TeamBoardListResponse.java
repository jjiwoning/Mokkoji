package com.ssafy.Mokkoji.core.trip.dto.response;

import com.ssafy.Mokkoji.core.trip.domain.TeamBoard;
import com.ssafy.Mokkoji.core.trip.domain.TeamBoardSpecification;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeamBoardListResponse {

    private Long boardId;
    private String title;
    private String nickname;
    private LocalDateTime createdDate;

    public TeamBoardListResponse(TeamBoardSpecification teamBoardSpecification) {
        this.boardId = teamBoardSpecification.getTeamBoardId();
        this.title = teamBoardSpecification.getTitle();
        this.nickname = teamBoardSpecification.getNickname();
        this.createdDate=teamBoardSpecification.getCreatedDate();
    }

}
