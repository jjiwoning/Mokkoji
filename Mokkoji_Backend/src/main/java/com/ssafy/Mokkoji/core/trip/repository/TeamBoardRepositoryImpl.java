package com.ssafy.Mokkoji.core.trip.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.Mokkoji.core.board.dto.request.BoardSearch;
import com.ssafy.Mokkoji.core.trip.domain.TeamBoard;
import com.ssafy.Mokkoji.core.trip.domain.TeamBoardSpecification;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.ssafy.Mokkoji.core.trip.domain.QTeamBoard.teamBoard;
import static com.ssafy.Mokkoji.core.user.domain.QUser.user;

public class TeamBoardRepositoryImpl implements TeamBoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public TeamBoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<TeamBoardSpecification> searchAllTeamBoard(BoardSearch boardSearch, Long tripTeamId) {

        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(boardSearch.getSearchString())) {
            builder.or(teamBoard.title.contains(boardSearch.getSearchString()));
            builder.or(teamBoard.content.contains(boardSearch.getSearchString()));
        }

        return queryFactory.select(
                        Projections.constructor(
                                TeamBoardSpecification.class,
                                teamBoard,
                                user
                        )
                )
                .from(teamBoard)
                .join(user).on(teamBoard.userId.eq(user.userId))
                .where(builder.and(teamBoard.tripTeam.tripTeamId.eq(tripTeamId)))
                .limit(boardSearch.getSize())
                .offset(boardSearch.getOffset())
                .orderBy(teamBoard.createdDate.asc())
                .fetch();
    }

    @Override
    public Optional<TeamBoardSpecification> findTeamBoardByTeamBoardId(Long teamBoardId) {
        return Optional.ofNullable(
                queryFactory.select(
                                Projections.constructor(
                                        TeamBoardSpecification.class,
                                        teamBoard,
                                        user
                                )
                        )
                        .from(teamBoard)
                        .join(user).on(teamBoard.userId.eq(user.userId))
                        .where(teamBoard.teamBoardId.eq(teamBoardId))
                        .fetchOne()
        );
    }

    @Override
    public boolean isTeamBoardWriter(Long userId, Long teamBoardId) {
        return queryFactory.selectFrom(teamBoard)
                .where(teamBoard.userId.eq(userId)
                        .and(teamBoard.teamBoardId.eq(teamBoardId)))
                .fetchFirst() != null;
    }

    @Override
    public void deleteTeamBoardByTeamId(Long tripTeamId) {
        queryFactory.delete(teamBoard)
                .where(teamBoard.tripTeam.tripTeamId.eq(tripTeamId))
                .execute();
    }

    @Override
    public List<TeamBoard> findAllTeamBoardByTripTeam(Long tripTeamId) {
        return queryFactory.selectFrom(teamBoard)
                .where(teamBoard.tripTeam.tripTeamId.eq(tripTeamId))
                .fetch();
    }
}
