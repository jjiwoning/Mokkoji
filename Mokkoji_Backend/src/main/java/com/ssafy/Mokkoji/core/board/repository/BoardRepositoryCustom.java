package com.ssafy.Mokkoji.core.board.repository;

import com.ssafy.Mokkoji.core.board.domain.Board;
import com.ssafy.Mokkoji.core.board.dto.request.BoardSearch;

import java.util.List;
import java.util.Optional;

public interface BoardRepositoryCustom {

    List<Board> searchAllBoard(BoardSearch boardSearch);

    Optional<Board> findBoardByIdWithImage(Long boardId);

    boolean isBoardWriter(Long userId, Long boardId);
}
