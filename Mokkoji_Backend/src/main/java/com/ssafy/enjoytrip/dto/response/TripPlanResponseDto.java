package com.ssafy.enjoytrip.dto.response;

import com.ssafy.enjoytrip.domain.TripPlan;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TripPlanResponseDto {

    private Long tripPlanId;

    private String planContent;

    private String planName;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<PlanAttractionResponseDto> planAttractions;

    public TripPlanResponseDto(TripPlan tripPlan) {
        this.tripPlanId = tripPlan.getTripPlanId();
        this.planContent = tripPlan.getPlanContent();
        this.planName = tripPlan.getPlanName();
        this.startDate = tripPlan.getStartDate();
        this.endDate = tripPlan.getEndDate();
        this.planAttractions = tripPlan.getPlanAttractions().stream().map(PlanAttractionResponseDto::new).collect(Collectors.toList());
    }
}
