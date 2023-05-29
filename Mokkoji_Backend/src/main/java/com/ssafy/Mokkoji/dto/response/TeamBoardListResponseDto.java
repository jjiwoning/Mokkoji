package com.ssafy.Mokkoji.dto.response;

import com.ssafy.Mokkoji.domain.TeamBoard;
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
