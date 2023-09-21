package com.ssafy.Mokkoji.core.trip.dto.response;

import com.ssafy.Mokkoji.core.trip.domain.TeamComment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeamCommentResponse {

    private Long commentId;

    private String content;

    private String nickname;

    private Long userId;

    private LocalDateTime createdDate;

    public TeamCommentResponse(TeamComment teamComment) {
        this.commentId = teamComment.getTeamCommentId();
        this.content = teamComment.getContent();
        this.nickname = teamComment.getUser().getNickname();
        this.userId = teamComment.getUser().getUserId();
        this.createdDate = teamComment.getCreatedDate();
    }

}
