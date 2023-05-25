package com.ssafy.enjoytrip.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.enjoytrip.domain.TripTeam;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.ssafy.enjoytrip.domain.QTripTeam.*;
import static com.ssafy.enjoytrip.domain.QUser.*;
import static com.ssafy.enjoytrip.domain.QUserTripTeam.*;

public class TripTeamRepositoryImpl implements TripTeamRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public TripTeamRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<TripTeam> getTripTeamByIdUsingJoin(Long tripTeamId) {
        TripTeam findTripTeam = queryFactory.selectFrom(tripTeam)
                .leftJoin(tripTeam.userTripTeams, userTripTeam).fetchJoin()
                .join(userTripTeam.user, user).fetchJoin()
                .where(tripTeam.tripTeamId.eq(tripTeamId))
                .orderBy(userTripTeam.teamRole.asc())
                .fetchOne();
        return Optional.ofNullable(findTripTeam);
    }

    @Override
    public List<TripTeam> findTripTeamListByUserId(Long userId) {
        return queryFactory.selectFrom(tripTeam)
                .join(tripTeam.userTripTeams, userTripTeam).fetchJoin()
                .where(userTripTeam.user.userId.eq(userId).and(userTripTeam.accepted.isTrue()))
                .fetch();
    }
}
