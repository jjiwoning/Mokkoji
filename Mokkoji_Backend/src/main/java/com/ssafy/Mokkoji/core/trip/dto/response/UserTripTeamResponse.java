package com.ssafy.Mokkoji.core.trip.dto.response;

import com.ssafy.Mokkoji.core.trip.domain.UserTripTeam;
import com.ssafy.Mokkoji.core.trip.domain.TeamRole;
import lombok.Data;

@Data
public class UserTripTeamResponse {

    private Long id;

    private String nickname;

    private TeamRole teamRole;

    public UserTripTeamResponse(UserTripTeam userTripTeam) {
        this.id = userTripTeam.getUserTripTeamId();
        this.nickname = userTripTeam.getUser().getNickname();
        this.teamRole = userTripTeam.getTeamRole();
    }
}
