package com.ssafy.enjoytrip.domain;

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
    public TeamBoard(Long teamBoardId, String content, String title, User user, TripTeam tripTeam) {
        this.teamBoardId = teamBoardId;
        this.content = content;
        this.title = title;
        this.user = user;
        this.tripTeam = tripTeam;
    }

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
