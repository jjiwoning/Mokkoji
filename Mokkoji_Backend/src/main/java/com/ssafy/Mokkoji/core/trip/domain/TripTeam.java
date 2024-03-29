package com.ssafy.Mokkoji.core.trip.domain;

import com.ssafy.Mokkoji.core.model.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "trip_teams")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TripTeam extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripTeamId;

    private String teamName;

    @OneToMany(mappedBy = "tripTeam", cascade = CascadeType.ALL)
    private List<UserTripTeam> userTripTeams = new ArrayList<>();

    @OneToMany(mappedBy = "tripTeam", cascade = CascadeType.ALL)
    private List<TripPlan> tripPlans = new ArrayList<>();

    @Builder
    public TripTeam(Long tripTeamId, String teamName) {
        this.tripTeamId = tripTeamId;
        this.teamName = teamName;
    }

    // 연관 관계 편의 메서드
    public void addUserTripTeam(final UserTripTeam userTripTeam) {
        userTripTeams.add(userTripTeam);
        userTripTeam.addTripTeam(this);
    }

    public void addTripPlans(final TripPlan tripPlan) {
        tripPlans.add(tripPlan);
        tripPlan.addTripTeam(this);
    }

    public void editTeamName(final String teamName) {
        this.teamName = teamName;
    }
}
