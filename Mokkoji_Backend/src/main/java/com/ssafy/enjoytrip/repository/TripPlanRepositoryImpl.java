package com.ssafy.enjoytrip.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.enjoytrip.domain.TripPlan;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.ssafy.enjoytrip.domain.QAttractionInfo.attractionInfo;
import static com.ssafy.enjoytrip.domain.QPlanAttraction.*;
import static com.ssafy.enjoytrip.domain.QTripPlan.*;
import static com.ssafy.enjoytrip.domain.QTripTeam.*;

public class TripPlanRepositoryImpl implements TripPlanRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public TripPlanRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<TripPlan> findTripPlanByIdJoinTripTeam(Long tripPlanId) {
        TripPlan findTripPlan = queryFactory
                .selectFrom(tripPlan)
                .join(tripPlan.tripTeam, tripTeam).fetchJoin()
                .where(tripPlan.tripPlanId.eq(tripPlanId))
                .fetchOne();
        return Optional.ofNullable(findTripPlan);
    }

    @Override
    public List<TripPlan> findTripPlanListByTripTeamId(Long tripTeamId) {
        return queryFactory
                .selectFrom(tripPlan)
                .where(tripPlan.tripTeam.tripTeamId.eq(tripTeamId))
                .fetch();
    }

    @Override
    public Optional<TripPlan> findTripPlanByIdJoinPlanAttraction(Long tripPlanId) {
        TripPlan findTripPlan = queryFactory
                .selectFrom(tripPlan)
                .leftJoin(tripPlan.planAttractions, planAttraction).fetchJoin()
                .leftJoin(planAttraction.attractionInfo, attractionInfo).fetchJoin()
                .where(tripPlan.tripPlanId.eq(tripPlanId))
                .orderBy(planAttraction.planOrder.asc())
                .fetchOne();
        return Optional.ofNullable(findTripPlan);
    }

    @Override
    public void deleteTripPlanByTripTeamId(Long tripTeamId) {
        List<TripPlan> result = queryFactory.selectFrom(tripPlan)
                .where(tripPlan.tripTeam.tripTeamId.eq(tripTeamId))
                .fetch();
        queryFactory.delete(planAttraction)
                        .where(planAttraction.tripPlan.in(result))
                                .execute();
        queryFactory.delete(tripPlan)
                .where(tripPlan.tripTeam.tripTeamId.eq(tripTeamId))
                .execute();
    }

    @Override
    public Optional<Long> findMaxOrder(Long tripPlanId) {
        return Optional.ofNullable(queryFactory
                .select(planAttraction.planOrder.max())
                .from(planAttraction)
                .where(planAttraction.tripPlan.tripPlanId.eq(tripPlanId))
                .fetchOne());
    }
}
