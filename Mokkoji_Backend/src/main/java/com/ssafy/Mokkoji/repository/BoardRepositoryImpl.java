package com.ssafy.Mokkoji.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.Mokkoji.domain.Board;
import com.ssafy.Mokkoji.dto.request.BoardSearch;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.ssafy.Mokkoji.domain.QBoard.*;
import static com.ssafy.Mokkoji.domain.QBoardImage.*;
import static com.ssafy.Mokkoji.domain.QUser.user;

public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Board> searchAllBoard(BoardSearch boardSearch) {

        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(boardSearch.getSearchString())) {
            builder.or(board.title.contains(boardSearch.getSearchString()));
            builder.or(board.content.contains(boardSearch.getSearchString()));
        }

        return queryFactory.selectFrom(board)
                .join(board.user, user).fetchJoin()
                .where(builder)
                .limit(boardSearch.getSize())
                .offset(boardSearch.getOffset())
                .orderBy(board.boardId.desc())
                .fetch();
    }

    @Override
    public Optional<Board> findBoardByBoardId(Long boardId) {
        Board board1 = queryFactory.selectFrom(board)
                .join(board.user, user).fetchJoin()
                .leftJoin(board.boardImages, boardImage).fetchJoin()
                .where(board.boardId.eq(boardId))
                .fetchOne();
        return Optional.ofNullable(board1);
    }

    @Override
    public boolean isBoardWriter(Long userId, Long boardId) {
        return queryFactory.selectFrom(board)
                .where(board.user.userId.eq(userId)
                        .and(board.boardId.eq(boardId)))
                .fetchFirst() != null;
    }
}
