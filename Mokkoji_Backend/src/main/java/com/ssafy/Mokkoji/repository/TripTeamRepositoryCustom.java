package com.ssafy.Mokkoji.repository;

import com.ssafy.Mokkoji.domain.TripTeam;

import java.util.List;
import java.util.Optional;

public interface TripTeamRepositoryCustom {

    Optional<TripTeam> getTripTeamByIdUsingJoin(Long tripTeamId);

    List<TripTeam> findTripTeamListByUserId(Long userId);
}
