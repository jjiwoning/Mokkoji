package com.ssafy.Mokkoji.service;

import com.ssafy.Mokkoji.domain.TripPlan;
import com.ssafy.Mokkoji.dto.request.TripPlanRequestDto;

import java.time.LocalDate;
import java.util.List;

public interface TripPlanService {

    void makeTripPlan(Long userId, Long tripTeamId, String planName, String planContent, LocalDate startDate, LocalDate endDate);

    void addPlanAttractions(Long userId, Long tripTeamId, Long tripPlanId, List<Integer> attractionIdList);

    void deleteTripPlan(Long userId, Long tripPlanId, Long tripTeamId);

    TripPlan getTripPlan(Long tripPlanId, Long tripTeamId, Long userId);

    List<TripPlan> getTripPlansByTripTeamId(Long tripTeamId);

    void deletePlanAttraction(Long userId, Long planAttractionId, Long tripTeamId);

    void editTripPlan(Long userId, Long tripPlanId, Long tripTeamId, TripPlanRequestDto tripPlanRequestDto);
}
