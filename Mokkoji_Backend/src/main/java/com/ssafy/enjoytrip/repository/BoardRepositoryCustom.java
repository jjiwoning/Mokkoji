package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.domain.Board;
import com.ssafy.enjoytrip.dto.request.BoardSearch;

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
