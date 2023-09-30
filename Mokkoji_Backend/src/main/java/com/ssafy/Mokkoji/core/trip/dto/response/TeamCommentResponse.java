package com.ssafy.Mokkoji.core.trip.dto.response;

import com.ssafy.Mokkoji.core.trip.domain.TeamComment;
import com.ssafy.Mokkoji.core.trip.domain.TeamCommentSpecification;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeamCommentResponse {

    private Long commentId;

    private String content;

    private String nickname;

    private Long userId;

    private LocalDateTime createdDate;

    public TeamCommentResponse(TeamCommentSpecification teamCommentSpecification) {
        this.commentId = teamCommentSpecification.getTeamCommentId();
        this.content = teamCommentSpecification.getContent();
        this.nickname = teamCommentSpecification.getNickname();
        this.userId = teamCommentSpecification.getUserId();
        this.createdDate = teamCommentSpecification.getCreatedDate();
    }

}
