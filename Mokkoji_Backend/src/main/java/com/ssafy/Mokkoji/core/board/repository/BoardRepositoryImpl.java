package com.ssafy.Mokkoji.core.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.Mokkoji.core.board.domain.BoardAndBoardImageSpecification;
import com.ssafy.Mokkoji.core.board.domain.BoardSpecification;
import com.ssafy.Mokkoji.core.board.dto.request.BoardSearch;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.ssafy.Mokkoji.core.board.domain.QBoard.board;
import static com.ssafy.Mokkoji.core.board.domain.QBoardImage.boardImage;
import static com.ssafy.Mokkoji.core.user.domain.QUser.user;


public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<BoardSpecification> searchAllBoard(BoardSearch boardSearch) {

        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(boardSearch.getSearchString())) {
            builder.or(board.title.contains(boardSearch.getSearchString()));
            builder.or(board.content.contains(boardSearch.getSearchString()));
        }

        return queryFactory.select(
                    Projections.constructor(
                            BoardSpecification.class,
                            board,
                            user
                    )
                )
                .from(board)
                .join(board.user, user).fetchJoin()
                .where(builder)
                .limit(boardSearch.getSize())
                .offset(boardSearch.getOffset())
                .orderBy(board.boardId.desc())
                .fetch();
    }

    @Override
    public Optional<BoardAndBoardImageSpecification> findBoardByIdWithImage(Long boardId) {
        return Optional.ofNullable(
                queryFactory.select(
                        Projections.constructor(
                                BoardAndBoardImageSpecification.class,
                                board,
                                user,
                                boardImage
                        )
                )
                .from(board)
                .join(board.user, user).fetchJoin()
                .leftJoin(board.boardImages, boardImage).fetchJoin()
                .where(board.boardId.eq(boardId))
                .fetchOne());
    }

    @Override
    public boolean isBoardWriter(Long userId, Long boardId) {
        return queryFactory.selectFrom(board)
                .where(board.user.userId.eq(userId)
                        .and(board.boardId.eq(boardId)))
                .fetchFirst() != null;
    }
}
