package com.ssafy.Mokkoji.domain;

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
    public TripPlan(
            final Long tripPlanId,
            final String planContent,
            final String planName,
            final LocalDate startDate,
            final LocalDate endDate,
            final List<PlanAttraction> planAttractions,
            final TripTeam tripTeam
    ) {
        this.tripPlanId = tripPlanId;
        this.planContent = planContent;
        this.planName = planName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.planAttractions = planAttractions;
        this.tripTeam = tripTeam;
    }

    public void addPlanAttraction(final PlanAttraction planAttraction){
        planAttractions.add(planAttraction);
        planAttraction.addTripPlan(this);
    }

    public void addTripTeam(final TripTeam tripTeam) {
        this.tripTeam = tripTeam;
    }

    public void editPlan(
            final String planName,
            final String planContent,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        this.planName = planName;
        this.planContent = planContent;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
