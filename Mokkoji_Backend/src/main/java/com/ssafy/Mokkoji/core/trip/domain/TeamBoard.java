package com.ssafy.Mokkoji.core.trip.domain;

import com.ssafy.Mokkoji.core.model.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "team_boards")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamBoard extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamBoardId;

    private String content;

    private String title;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_team_id")
    private TripTeam tripTeam;

    @Builder
    public TeamBoard(
            final Long teamBoardId,
            final String content,
            final String title,
            final Long userId,
            final TripTeam tripTeam
    ) {
        this.teamBoardId = teamBoardId;
        this.content = content;
        this.title = title;
        this.userId = userId;
        this.tripTeam = tripTeam;
    }

    public void updateBoard(final String title, final String content) {
        this.title = title;
        this.content = content;
    }
}
