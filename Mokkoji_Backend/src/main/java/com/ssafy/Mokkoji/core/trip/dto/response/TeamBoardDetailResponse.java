package com.ssafy.Mokkoji.core.trip.dto.response;

import com.ssafy.Mokkoji.core.trip.domain.TeamBoard;
import com.ssafy.Mokkoji.core.trip.domain.TeamBoardSpecification;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeamBoardDetailResponse {

    private Long teamBoardId;
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime createdDate;

    public TeamBoardDetailResponse(TeamBoardSpecification teamBoardSpecification) {
        this.teamBoardId = teamBoardSpecification.getTeamBoardId();
        this.title = teamBoardSpecification.getTitle();
        this.content = teamBoardSpecification.getContent();
        this.nickname = teamBoardSpecification.getNickname();
        this.createdDate = teamBoardSpecification.getCreatedDate();
    }

}
