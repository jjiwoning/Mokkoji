package com.ssafy.Mokkoji.core.trip.dto.response;

import com.ssafy.Mokkoji.core.trip.domain.UserTripTeam;
import lombok.Data;

@Data
public class UserTripTeamForm {

    Long userTripTeamId;

    Long tripTeamId;

    String teamName;

    public UserTripTeamForm(UserTripTeam userTripTeam) {
        this.userTripTeamId = userTripTeam.getUserTripTeamId();
        this.tripTeamId = userTripTeam.getTripTeam().getTripTeamId();
        this.teamName = userTripTeam.getTripTeam().getTeamName();
    }
}
