package com.ssafy.enjoytrip.dto.response;

import com.ssafy.enjoytrip.domain.Comment;
import com.ssafy.enjoytrip.domain.TeamComment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeamCommentResponseDto {

    private Long commentId;

    private String content;

    private String nickname;

    private Long userId;

    private LocalDateTime createdDate;

    public TeamCommentResponseDto(TeamComment teamComment) {
        this.commentId = teamComment.getTeamCommentId();
        this.content = teamComment.getContent();
        this.nickname = teamComment.getUser().getNickname();
        this.userId = teamComment.getUser().getUserId();
        this.createdDate = teamComment.getCreatedDate();
    }

}
