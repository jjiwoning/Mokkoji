package com.ssafy.enjoytrip.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.enjoytrip.domain.UserTripTeam;
import com.ssafy.enjoytrip.domain.team_relation.TeamRole;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.ssafy.enjoytrip.domain.QTripTeam.*;
import static com.ssafy.enjoytrip.domain.QUser.*;
import static com.ssafy.enjoytrip.domain.QUserTripTeam.*;

public class UserTripTeamRepositoryImpl implements UserTripTeamRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public UserTripTeamRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<UserTripTeam> getUserTripTeamByUserIdAndTeamId(Long userId, Long teamId) {

        UserTripTeam findUserTripTeam = queryFactory.selectFrom(userTripTeam)
                .join(userTripTeam.tripTeam, tripTeam).fetchJoin()
                .join(userTripTeam.user, user).fetchJoin()
                .where(userTripTeam.user.userId.eq(userId).and(userTripTeam.tripTeam.tripTeamId.eq(teamId)))
                .fetchOne();

        return Optional.ofNullable(findUserTripTeam);
    }

    @Override
    public boolean existsByUserIdAndTeamId(Long userId, Long teamId) {
        return queryFactory.from(userTripTeam)
                .where(userTripTeam.user.userId.eq(userId).and(userTripTeam.tripTeam.tripTeamId.eq(teamId)))
                .select(userTripTeam.userTripTeamId)
                .fetchFirst() != null;
    }

    @Override
    public List<UserTripTeam> findAllUserTripTeamByUserId(Long userId) {
        return queryFactory.selectFrom(userTripTeam)
                .join(userTripTeam.tripTeam, tripTeam).fetchJoin()
                .where(userTripTeam.accepted.eq(false).and(userTripTeam.user.userId.eq(userId)))
                .fetch();
    }

    @Override
    public void deleteUserTripTeamByTripTeamId(Long tripTeamId) {
        queryFactory.delete(userTripTeam)
                .where(userTripTeam.tripTeam.tripTeamId.eq(tripTeamId))
                .execute();
    }

    @Override
    public boolean isUserLeader(Long userId, Long tripTeamId) {
        return queryFactory.selectFrom(userTripTeam)
                .where(userTripTeam.user.userId.eq(userId)
                        .and(userTripTeam.tripTeam.tripTeamId.eq(tripTeamId))
                        .and(userTripTeam.teamRole.eq(TeamRole.LEADER))
                )
                .fetchFirst() != null;
    }
}
