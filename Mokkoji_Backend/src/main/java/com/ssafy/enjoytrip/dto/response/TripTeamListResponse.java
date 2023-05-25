package com.ssafy.enjoytrip.dto.response;

import com.ssafy.enjoytrip.domain.TripTeam;
import com.ssafy.enjoytrip.domain.team_relation.TeamRole;
import lombok.Data;

@Data
public class TripTeamListResponse {

    private Long tripTeamId;

    private String teamName;

    private TeamRole teamRole;

    public TripTeamListResponse(TripTeam tripTeam) {
        this.tripTeamId = tripTeam.getTripTeamId();
        this.teamName = tripTeam.getTeamName();
        this.teamRole = tripTeam.getUserTripTeams().get(0).getTeamRole();
    }
}
