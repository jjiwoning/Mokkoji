package com.ssafy.Mokkoji.core.board.dto.response;

import com.ssafy.Mokkoji.core.board.domain.CommentSpecification;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponse {

    private Long commentId;

    private String content;

    private Long userId;

    private String nickname;

    private LocalDateTime createdDate;

    public CommentResponse(CommentSpecification commentSpecification) {
        this.commentId = commentSpecification.getCommentId();
        this.content = commentSpecification.getContent();
        this.userId = commentSpecification.getUserId();
        this.nickname = commentSpecification.getNickname();
        this.createdDate = commentSpecification.getCreatedDate();
    }
}
