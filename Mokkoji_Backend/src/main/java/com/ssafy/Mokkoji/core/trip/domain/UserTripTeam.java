package com.ssafy.Mokkoji.core.trip.domain;

import com.ssafy.Mokkoji.core.model.BaseTimeEntity;
import com.ssafy.Mokkoji.core.user.domain.User;
import lombok.*;

import javax.persistence.*;

/**
 * User <-> Team mapping table 전용 클래스
 */
@Table(name = "user_trip_teams")
@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserTripTeam extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userTripTeamId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_team_id")
    private TripTeam tripTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private TeamRole teamRole; // LEADER, MEMBER

    private boolean accepted; // 초대 수락 여부 (true: 수락, false: 대기)

    public void addTripTeam(final TripTeam tripTeam) {
        this.tripTeam = tripTeam;
    }

    public void acceptInvite() {
        this.accepted = true;
    }

    public boolean isLeader() {
        return this.teamRole == TeamRole.LEADER;
    }
}
