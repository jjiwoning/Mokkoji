package com.ssafy.Mokkoji.core.trip.repository;

import com.ssafy.Mokkoji.core.trip.domain.UserTripTeam;

import java.util.List;
import java.util.Optional;

public interface UserTripTeamRepositoryCustom {

    Optional<UserTripTeam> getUserTripTeamByUserIdAndTeamId(Long userId, Long teamId);

    boolean existsByUserIdAndTeamId(Long userId, Long teamId);

    List<UserTripTeam> findAllUserTripTeamByUserId(Long userId);

    void deleteUserTripTeamByTripTeamId(Long tripTeamId);

    boolean isUserLeader(Long userId, Long tripTeamId);
}
