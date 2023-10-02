package com.ssafy.Mokkoji.core.trip.service;

import com.ssafy.Mokkoji.core.trip.domain.TripTeam;
import com.ssafy.Mokkoji.core.trip.domain.UserTripTeam;
import com.ssafy.Mokkoji.core.trip.dto.request.TripTeamUpdateRequest;
import com.ssafy.Mokkoji.core.trip.dto.request.UserInviteRequest;

import java.util.List;

public interface TripTeamService {
    void makeTripTeam(Long userId, String teamName);

    void editTripTeam(Long userId, Long tripTeamId, TripTeamUpdateRequest request);

    void deleteTripTeam(Long tripTeamId, Long userId);

    TripTeam findTripTeam(Long tripTeamId);

    void inviteUser(Long leaderId, UserInviteRequest request);

    void acceptInvite(Long userTripTeamId, Long userId, Long teamId);

    void refuseInvite(Long userTripTeamId, Long userId, Long teamId);

    UserTripTeam getUserTripTeam(Long userTripTeamId);

    List<UserTripTeam> getAllUserTripTeam(Long userId);

    boolean validUserIsLeader(Long userId, Long tripTeamId);

    List<TripTeam> getAllTripTeamByUserId(Long userId);
}
