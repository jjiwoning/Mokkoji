package com.ssafy.Mokkoji.core.board.service;

import com.ssafy.Mokkoji.core.board.domain.Board;
import com.ssafy.Mokkoji.core.board.domain.BoardImage;
import com.ssafy.Mokkoji.core.board.dto.request.BoardSearch;

import java.util.List;

public interface BoardService {
	
    Board getBoardDetail(Long boardId);

    List<Board> getAllBoards(BoardSearch boardSearch);

    void deleteBoard(Long id, Long userId);

    void addBoard(Board board, Long userId, List<BoardImage> boardImages);

    void updateBoard(Long boardId, String title, String content, List<BoardImage> boardImages);

    boolean isBoardWriter(Long userId, Long boardId);
}
