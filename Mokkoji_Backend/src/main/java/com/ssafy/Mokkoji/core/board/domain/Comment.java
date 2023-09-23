package com.ssafy.Mokkoji.core.board.domain;

import com.ssafy.Mokkoji.core.model.BaseTimeEntity;
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

    private String content;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Comment(
            final Long commentId,
            final String content,
            final Long userId,
            final Board board
    ) {
        this.commentId = commentId;
        this.content = content;
        this.userId = userId;
        this.board = board;
    }

    public void editComment(final String content) {
        this.content = content;
    }

    public boolean isSameUser(final Long userId) {
        return Objects.equals(this.userId, userId);
    }
}
