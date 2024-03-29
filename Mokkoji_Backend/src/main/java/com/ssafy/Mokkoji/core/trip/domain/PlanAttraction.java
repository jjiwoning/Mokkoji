package com.ssafy.Mokkoji.core.trip.domain;

import com.ssafy.Mokkoji.core.attraction.domain.AttractionInfo;
import com.ssafy.Mokkoji.core.model.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

/**
 * plan <-> Attraction 매핑 테이블 전용 클래스
 */
@Table(name = "plan_attractions")
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

    public void addTripPlan(final TripPlan tripPlan){
        this.tripPlan = tripPlan;
    }
}
