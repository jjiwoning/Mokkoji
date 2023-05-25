package com.ssafy.enjoytrip.dto.response;

import com.ssafy.enjoytrip.domain.TripTeam;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TripTeamResponseDto {

    private Long tripTeamId;

    private String teamName;

    private List<UserTripTeamResponseDto> userTripTeams;

    public TripTeamResponseDto(TripTeam tripTeam) {
        this.tripTeamId = tripTeam.getTripTeamId();
        this.teamName = tripTeam.getTeamName();
        this.userTripTeams
                = tripTeam.getUserTripTeams().stream().map(UserTripTeamResponseDto::new).collect(Collectors.toList());
    }
}
