package com.ssafy.Mokkoji.repository;

import com.ssafy.Mokkoji.domain.Board;
import com.ssafy.Mokkoji.dto.request.BoardSearch;

import java.util.List;
import java.util.Optional;

public interface BoardRepositoryCustom {

    /**
     * 게시판 정보 조회 ->
     */
    List<Board> searchAllBoard(BoardSearch boardSearch);

    Optional<Board> findBoardByBoardId(Long boardId);

    boolean isBoardWriter(Long userId, Long boardId);
}
