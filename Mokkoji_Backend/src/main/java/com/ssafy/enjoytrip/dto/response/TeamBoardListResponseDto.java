package com.ssafy.enjoytrip.dto.response;

import com.ssafy.enjoytrip.domain.Board;
import com.ssafy.enjoytrip.domain.TeamBoard;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeamBoardListResponseDto {

    private Long boardId;
    private String title;
    private String nickname;
    private LocalDateTime createdDate;

    public TeamBoardListResponseDto(TeamBoard teamBoard) {
        this.boardId = teamBoard.getTeamBoardId();
        this.title = teamBoard.getTitle();
        this.nickname = teamBoard.getUser().getNickname();
        this.createdDate=teamBoard.getCreatedDate();
    }

}
