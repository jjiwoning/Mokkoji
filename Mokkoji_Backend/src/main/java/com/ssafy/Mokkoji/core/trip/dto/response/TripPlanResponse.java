package com.ssafy.Mokkoji.core.trip.dto.response;

import com.ssafy.Mokkoji.core.trip.domain.TripPlan;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TripPlanResponse {

    private Long tripPlanId;

    private String planContent;

    private String planName;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<PlanAttractionResponse> planAttractions;

    public TripPlanResponse(TripPlan tripPlan) {
        this.tripPlanId = tripPlan.getTripPlanId();
        this.planContent = tripPlan.getPlanContent();
        this.planName = tripPlan.getPlanName();
        this.startDate = tripPlan.getStartDate();
        this.endDate = tripPlan.getEndDate();
        this.planAttractions = tripPlan.getPlanAttractions().stream().map(PlanAttractionResponse::new).collect(Collectors.toList());
    }
}
