package com.ssafy.enjoytrip.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.enjoytrip.domain.Comment;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.ssafy.enjoytrip.domain.QBoard.*;
import static com.ssafy.enjoytrip.domain.QComment.*;
import static com.ssafy.enjoytrip.domain.QUser.*;

public class CommentRepositoryImpl implements CommentRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public CommentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Comment> getAllCommentByBoardId(Long boardId) {
        return queryFactory
                .selectFrom(comment)
                .innerJoin(comment.board, board).fetchJoin()
                .where(board.boardId.eq(boardId))
                .orderBy(comment.createdDate.desc())
                .fetch();
    }

    @Override
    public Optional<Comment> findCommentByIdUsingFetchJoin(Long commentId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(comment)
                .innerJoin(comment.board, board).fetchJoin()
                .innerJoin(comment.user, user).fetchJoin()
                .where(comment.commentId.eq(commentId))
                .orderBy(comment.createdDate.asc())
                .fetchOne());
    }

    @Override
    public void deleteCommentByBoardId(Long boardId) {
        queryFactory.delete(comment)
                .where(comment.board.boardId.eq(boardId))
                .execute();
    }

    @Override
    public boolean isCommentWriter(Long userId, Long commentId) {
        return queryFactory.selectFrom(comment)
                .where(comment.user.userId.eq(userId)
                        .and(comment.commentId.eq(commentId)))
                .fetchFirst() != null;
    }
}
