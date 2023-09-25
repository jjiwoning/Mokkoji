package com.ssafy.Mokkoji.core.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.Mokkoji.core.board.domain.BoardAndBoardImageSpecification;
import com.ssafy.Mokkoji.core.board.domain.BoardImage;
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
                .join(user).on(board.userId.eq(user.userId))
                .where(builder)
                .limit(boardSearch.getSize())
                .offset(boardSearch.getOffset())
                .orderBy(board.boardId.desc())
                .fetch();
    }

    @Override
    public Optional<BoardAndBoardImageSpecification> findBoardByIdWithImage(Long boardId) {
        List<BoardAndBoardImageSpecification> results = queryFactory.select(
                        Projections.constructor(
                                BoardAndBoardImageSpecification.class,
                                board,
                                user,
                                Projections.list(Projections.fields(BoardImage.class,
                                        boardImage.boardImageId,
                                        boardImage.userFileName,
                                        boardImage.storedFileName,
                                        boardImage.board))
                        )
                )
                .from(board)
                .join(user).on(board.userId.eq(user.userId))
                .leftJoin(board.boardImages, boardImage).fetchJoin()
                .where(board.boardId.eq(boardId))
                .fetch();

        if (results.isEmpty()) {
            return Optional.empty();
        }

        BoardAndBoardImageSpecification result = results.get(0);

        for (int i = 1; i < results.size(); i++) {
            result.getBoardImages().addAll(results.get(i).getBoardImages());
        }

        return Optional.ofNullable(result);
    }

    @Override
    public boolean isBoardWriter(Long userId, Long boardId) {
        return queryFactory.selectFrom(board)
                .where(board.userId.eq(userId).and(board.boardId.eq(boardId)))
                .fetchFirst() != null;
    }
}
