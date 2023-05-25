package com.ssafy.enjoytrip.domain;

import lombok.*;

import javax.persistence.*;

/**
 * plan <-> Attraction 매핑 테이블 전용 클래스
 */
@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanAttraction extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planAttractionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attraction_id")
    private AttractionInfo attractionInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_plan_id")
    private TripPlan tripPlan;

    private Long planOrder;

    public void addTripPlan(TripPlan tripPlan){
        this.tripPlan = tripPlan;
    }
}
