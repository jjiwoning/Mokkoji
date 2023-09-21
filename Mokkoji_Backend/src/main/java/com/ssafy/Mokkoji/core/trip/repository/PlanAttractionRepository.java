package com.ssafy.Mokkoji.core.trip.repository;

import com.ssafy.Mokkoji.core.trip.domain.PlanAttraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlanAttractionRepository extends JpaRepository<PlanAttraction, Long> {

    @Modifying
    @Query("delete from PlanAttraction p where p.tripPlan.tripPlanId = :tripPlanId")
    void deletePlanAttractionsByTripPlanId(@Param("tripPlanId") Long tripPlanId);

}
