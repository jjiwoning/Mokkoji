package com.ssafy.enjoytrip.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class TripPlan extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripPlanId;

    private String planContent;

    private String planName;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(mappedBy = "tripPlan", cascade = CascadeType.ALL)
    private List<PlanAttraction> planAttractions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_team_id")
    private TripTeam tripTeam;

    @Builder
    public TripPlan(Long tripPlanId, String planContent, String planName, LocalDate startDate, LocalDate endDate, List<PlanAttraction> planAttractions, TripTeam tripTeam) {
        this.tripPlanId = tripPlanId;
        this.planContent = planContent;
        this.planName = planName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.planAttractions = planAttractions;
        this.tripTeam = tripTeam;
    }

    public void addPlanAttraction(PlanAttraction planAttraction){
        planAttractions.add(planAttraction);
        planAttraction.addTripPlan(this);
    }

    public void addTripTeam(TripTeam tripTeam) {
        this.tripTeam = tripTeam;
    }

    public void editPlan(String planName, String planContent, LocalDate startDate, LocalDate endDate) {
        this.planName = planName;
        this.planContent = planContent;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
