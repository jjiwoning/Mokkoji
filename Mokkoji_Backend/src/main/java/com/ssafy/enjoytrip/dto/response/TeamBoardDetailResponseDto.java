package com.ssafy.enjoytrip.dto.response;

import com.ssafy.enjoytrip.domain.TeamBoard;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeamBoardDetailResponseDto {

    private Long teamBoardId;
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime createdDate;

    public TeamBoardDetailResponseDto(TeamBoard teamBoard) {
        this.teamBoardId = teamBoard.getTeamBoardId();
        this.title = teamBoard.getTitle();
        this.content = teamBoard.getContent();
        this.nickname = teamBoard.getUser().getNickname();
        this.createdDate = teamBoard.getCreatedDate();
    }

}
