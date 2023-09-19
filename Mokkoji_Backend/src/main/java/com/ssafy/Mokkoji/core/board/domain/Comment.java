package com.ssafy.Mokkoji.core.board.domain;

import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Comment(
            final Long commentId,
            final String content,
            final User user,
            final Board board
    ) {
        this.commentId = commentId;
        this.content = content;
        this.user = user;
        this.board = board;
    }

    public void editComment(final String content) {
        this.content = content;
    }
}
