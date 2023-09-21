package com.ssafy.Mokkoji.core.trip.dto.response;

import com.ssafy.Mokkoji.core.trip.domain.TripPlan;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TripPlanListResponse {

    private Long tripPlanId;

    private String planContent;

    private String planName;

    private LocalDate startDate;

    private LocalDate endDate;

    public TripPlanListResponse(TripPlan tripPlan) {
        this.tripPlanId = tripPlan.getTripPlanId();
        this.planContent = tripPlan.getPlanContent();
        this.planName = tripPlan.getPlanName();
        this.startDate = tripPlan.getStartDate();
        this.endDate = tripPlan.getEndDate();
    }
}
