package com.ssafy.enjoytrip.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.enjoytrip.domain.TeamBoard;
import com.ssafy.enjoytrip.dto.request.BoardSearch;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static com.ssafy.enjoytrip.domain.QTeamBoard.teamBoard;
import static com.ssafy.enjoytrip.domain.QUser.user;

public class TeamBoardRepositoryImpl implements TeamBoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public TeamBoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<TeamBoard> searchAllTeamBoard(BoardSearch boardSearch, Long tripTeamId) {

        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(boardSearch.getSearchString())) {
            builder.or(teamBoard.title.contains(boardSearch.getSearchString()));
            builder.or(teamBoard.content.contains(boardSearch.getSearchString()));
        }

        return queryFactory.selectFrom(teamBoard)
                .join(teamBoard.user, user).fetchJoin()
                .where(builder.and(teamBoard.tripTeam.tripTeamId.eq(tripTeamId)))
                .limit(boardSearch.getSize())
                .offset(boardSearch.getOffset())
                .orderBy(teamBoard.createdDate.asc())
                .fetch();
    }

    @Override
    public Optional<TeamBoard> findTeamBoardByTeamBoardId(Long teamBoardId) {
        return Optional.ofNullable(
                queryFactory.selectFrom(teamBoard)
                        .join(teamBoard.user, user).fetchJoin()
                        .where(teamBoard.teamBoardId.eq(teamBoardId))
                        .fetchOne()
        );
    }

    @Override
    public boolean isTeamBoardWriter(Long userId, Long teamBoardId) {
        return queryFactory.selectFrom(teamBoard)
                .where(teamBoard.user.userId.eq(userId)
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
                .join(teamBoard.user, user).fetchJoin()
                .where(teamBoard.tripTeam.tripTeamId.eq(tripTeamId))
                .fetch();
    }
}
