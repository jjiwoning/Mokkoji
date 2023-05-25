package com.ssafy.enjoytrip.dto.response;

import com.ssafy.enjoytrip.domain.Comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDto {

    private Long commentId;

    private String content;

    private Long userId;

    private String nickname;

    private LocalDateTime createdDate;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.userId = comment.getUser().getUserId();
        this.nickname = comment.getUser().getNickname();
        this.createdDate = comment.getCreatedDate();
    }
}
