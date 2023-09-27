package com.ssafy.Mokkoji.core.board.domain;

import com.ssafy.Mokkoji.core.model.BaseTimeEntity;
import com.ssafy.Mokkoji.core.model.Content;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Embedded
    private Content content;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    private Comment(
            final Long commentId,
            final Content content,
            final Long userId,
            final Board board
    ) {
        this.commentId = commentId;
        this.content = content;
        this.userId = userId;
        this.board = board;
    }

    public static Comment of(
            final String content,
            final Long userId,
            final Board board
    ) {
        return Comment.builder()
                .content(Content.from(content))
                .userId(userId)
                .board(board)
                .build();
    }

    public void editComment(final String content) {
        this.content = Content.from(content);
    }

    public boolean isSameUser(final Long userId) {
        return Objects.equals(this.userId, userId);
    }
}
