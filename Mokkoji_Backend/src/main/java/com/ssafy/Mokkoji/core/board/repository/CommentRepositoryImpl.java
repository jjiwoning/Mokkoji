package com.ssafy.Mokkoji.core.board.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.Mokkoji.core.board.domain.CommentSpecification;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.Mokkoji.core.board.domain.QBoard.board;
import static com.ssafy.Mokkoji.core.board.domain.QComment.comment;
import static com.ssafy.Mokkoji.core.user.domain.QUser.user;


public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CommentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<CommentSpecification> getAllCommentByBoardId(final Long boardId) {
        return queryFactory
                .select(
                        Projections.constructor(
                        CommentSpecification.class,
                        comment,
                        user)
                )
                .from(comment)
                .join(user).on(comment.userId.eq(user.userId))
                .where(comment.board.boardId.eq(boardId))
                .orderBy(comment.createdDate.desc())
                .fetch();
    }

    @Override
    public void deleteCommentByBoardId(final Long boardId) {
        queryFactory.delete(comment)
                .where(comment.board.boardId.eq(boardId))
                .execute();
    }

    @Override
    public boolean isCommentWriter(final Long userId, final Long commentId) {
        return queryFactory.selectFrom(comment)
                .where(comment.userId.eq(userId).and(comment.commentId.eq(commentId)))
                .fetchFirst() != null;
    }
}
