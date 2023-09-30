package com.ssafy.Mokkoji.core.trip.domain;

import com.ssafy.Mokkoji.core.model.BaseTimeEntity;
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

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_board_id")
    private TeamBoard teamBoard;

    @Builder
    public TeamComment(
            final Long teamCommentId,
            final String content,
            final Long userId,
            final TeamBoard teamBoard
    ) {
        this.teamCommentId = teamCommentId;
        this.content = content;
        this.userId = userId;
        this.teamBoard = teamBoard;
    }

    public void editComment(final String content) {
        this.content = content;
    }
}
