package com.ssafy.Mokkoji.core.trip.dto.response;

import com.ssafy.Mokkoji.core.trip.domain.TripTeam;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TripTeamResponse {

    private Long tripTeamId;

    private String teamName;

    private List<UserTripTeamResponse> userTripTeams;

    public TripTeamResponse(TripTeam tripTeam) {
        this.tripTeamId = tripTeam.getTripTeamId();
        this.teamName = tripTeam.getTeamName();
        this.userTripTeams
                = tripTeam.getUserTripTeams().stream().map(UserTripTeamResponse::new).collect(Collectors.toList());
    }
}
