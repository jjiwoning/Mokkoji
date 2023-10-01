package com.ssafy.Mokkoji.core.trip.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.Mokkoji.core.trip.domain.TeamBoard;
import com.ssafy.Mokkoji.core.trip.domain.TeamCommentSpecification;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.Mokkoji.core.trip.domain.QTeamBoard.teamBoard;
import static com.ssafy.Mokkoji.core.trip.domain.QTeamComment.teamComment;
import static com.ssafy.Mokkoji.core.user.domain.QUser.user;

public class TeamCommentRepositoryImpl implements TeamCommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public TeamCommentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<TeamCommentSpecification> getAllTeamCommentByBoardId(Long teamBoardId) {
        return queryFactory
                .select(
                        Projections.constructor(
                                TeamCommentSpecification.class,
                                teamComment,
                                user
                        )
                )
                .from(teamComment)
                .join(user).on(user.userId.eq(teamComment.userId))
                .innerJoin(teamComment.teamBoard, teamBoard).fetchJoin()
                .where(teamBoard.teamBoardId.eq(teamBoardId))
                .orderBy(teamComment.createdDate.desc())
                .fetch();
    }

    @Override
    public void deleteTeamCommentByBoardId(Long teamBoardId) {
        queryFactory.delete(teamComment)
                .where(teamComment.teamBoard.teamBoardId.eq(teamBoardId))
                .execute();
    }

    @Override
    public boolean isTeamCommentWriter(Long userId, Long teamCommentId) {
        return queryFactory.selectFrom(teamComment)
                .where(teamComment.userId.eq(userId)
                        .and(teamComment.teamCommentId.eq(teamCommentId)))
                .fetchFirst() != null;
    }

    @Override
    public void deleteAllCommentByTeam(List<TeamBoard> teamBoards) {
        queryFactory.delete(teamComment)
                .where(teamComment.teamBoard.in(teamBoards))
                .execute();
    }
}
