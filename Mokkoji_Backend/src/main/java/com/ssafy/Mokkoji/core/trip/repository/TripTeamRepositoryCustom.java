package com.ssafy.Mokkoji.core.trip.repository;

import com.ssafy.Mokkoji.core.trip.domain.TripTeam;

import java.util.List;
import java.util.Optional;

public interface TripTeamRepositoryCustom {

    Optional<TripTeam> getTripTeamByIdUsingJoin(Long tripTeamId);

    List<TripTeam> findTripTeamListByUserId(Long userId);
}
