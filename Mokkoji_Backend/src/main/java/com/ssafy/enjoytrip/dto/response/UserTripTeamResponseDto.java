package com.ssafy.enjoytrip.dto.response;

import com.ssafy.enjoytrip.domain.UserTripTeam;
import com.ssafy.enjoytrip.domain.team_relation.TeamRole;
import lombok.Data;

@Data
public class UserTripTeamResponseDto {

    private Long id;

    private String nickname;

    private TeamRole teamRole;

    public UserTripTeamResponseDto(UserTripTeam userTripTeam) {
        this.id = userTripTeam.getUserTripTeamId();
        this.nickname = userTripTeam.getUser().getNickname();
        this.teamRole = userTripTeam.getTeamRole();
    }
}
