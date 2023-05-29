package com.ssafy.Mokkoji.dto.response;

import com.ssafy.Mokkoji.domain.UserTripTeam;
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
