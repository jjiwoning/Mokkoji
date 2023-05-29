package com.ssafy.Mokkoji.repository;

import com.ssafy.Mokkoji.domain.TripPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripPlanRepository extends JpaRepository<TripPlan, Long>, TripPlanRepositoryCustom {

}
