package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.domain.Comment;
import com.ssafy.enjoytrip.domain.TripPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripPlanRepository extends JpaRepository<TripPlan, Long>, TripPlanRepositoryCustom {

}
