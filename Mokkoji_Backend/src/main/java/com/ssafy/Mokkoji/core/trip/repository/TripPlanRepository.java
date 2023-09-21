package com.ssafy.Mokkoji.core.trip.repository;

import com.ssafy.Mokkoji.core.trip.domain.TripPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripPlanRepository extends JpaRepository<TripPlan, Long>, TripPlanRepositoryCustom {

}
