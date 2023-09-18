package com.ssafy.Mokkoji.domain;

import com.ssafy.Mokkoji.core.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamComment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamCommentId;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_board_id")
    private TeamBoard teamBoard;

    @Builder
    public TeamComment(
            final Long teamCommentId,
            final String content,
            final User user,
            final TeamBoard teamBoard
    ) {
        this.teamCommentId = teamCommentId;
        this.content = content;
        this.user = user;
        this.teamBoard = teamBoard;
    }

    public void editComment(final String content) {
        this.content = content;
    }
}
