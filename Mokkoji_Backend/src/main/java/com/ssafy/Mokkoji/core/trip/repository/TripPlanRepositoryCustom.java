package com.ssafy.Mokkoji.core.trip.repository;

import com.ssafy.Mokkoji.core.trip.domain.TripPlan;

import java.util.List;
import java.util.Optional;

public interface TripPlanRepositoryCustom {
    Optional<TripPlan> findTripPlanByIdJoinTripTeam(Long tripPlanId);

    List<TripPlan> findTripPlanListByTripTeamId(Long tripTeamId);

    Optional<TripPlan> findTripPlanByIdJoinPlanAttraction(Long tripPlanId);

    void deleteTripPlanByTripTeamId(Long tripTeamId);

    Optional<Long> findMaxOrder(Long tripPlanId);
}
