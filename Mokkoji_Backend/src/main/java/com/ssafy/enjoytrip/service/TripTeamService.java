package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.domain.TripTeam;
import com.ssafy.enjoytrip.domain.UserTripTeam;

import java.util.List;

public interface TripTeamService {
    void makeTripTeam(Long userId, String teamName);

    void editTripTeam(Long userId, Long tripTeamId, String teamName);

    void deleteTripTeam(Long tripTeamId, Long userId);

    TripTeam findTripTeam(Long tripTeamId);

    void inviteUser(Long leaderId, Long userId, Long teamId);

    void acceptInvite(Long userTripTeamId, Long userId, Long teamId);

    void refuseInvite(Long userTripTeamId, Long userId, Long teamId);

    UserTripTeam getUserTripTeam(Long userTripTeamId);

    List<UserTripTeam> getAllUserTripTeam(Long userId);

    boolean validUserIsLeader(Long userId, Long tripTeamId);

    List<TripTeam> getAllTripTeamByUserId(Long userId);
}
