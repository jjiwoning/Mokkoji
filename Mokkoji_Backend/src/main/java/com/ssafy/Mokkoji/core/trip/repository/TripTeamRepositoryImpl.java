package com.ssafy.Mokkoji.core.trip.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.Mokkoji.core.trip.domain.TripTeam;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.ssafy.Mokkoji.core.trip.domain.QTripTeam.tripTeam;
import static com.ssafy.Mokkoji.core.trip.domain.QUserTripTeam.userTripTeam;
import static com.ssafy.Mokkoji.core.user.domain.QUser.user;

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
