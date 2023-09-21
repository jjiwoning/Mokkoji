package com.ssafy.Mokkoji.core.trip.domain;

import com.ssafy.Mokkoji.core.model.BaseTimeEntity;
import com.ssafy.Mokkoji.core.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamBoard extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamBoardId;

    private String content;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_team_id")
    private TripTeam tripTeam;

    @Builder
    public TeamBoard(
            final Long teamBoardId,
            final String content,
            final String title,
            final User user,
            final TripTeam tripTeam
    ) {
        this.teamBoardId = teamBoardId;
        this.content = content;
        this.title = title;
        this.user = user;
        this.tripTeam = tripTeam;
    }

    public void updateBoard(final String title, final String content) {
        this.title = title;
        this.content = content;
    }

    public void setUser(final User user) {
        this.user = user;
    }

}
