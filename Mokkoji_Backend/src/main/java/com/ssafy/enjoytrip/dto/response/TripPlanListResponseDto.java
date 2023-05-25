package com.ssafy.enjoytrip.dto.response;

import com.ssafy.enjoytrip.domain.TripPlan;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TripPlanListResponseDto {

    private Long tripPlanId;

    private String planContent;

    private String planName;

    private LocalDate startDate;

    private LocalDate endDate;

    public TripPlanListResponseDto(TripPlan tripPlan) {
        this.tripPlanId = tripPlan.getTripPlanId();
        this.planContent = tripPlan.getPlanContent();
        this.planName = tripPlan.getPlanName();
        this.startDate = tripPlan.getStartDate();
        this.endDate = tripPlan.getEndDate();
    }
}
