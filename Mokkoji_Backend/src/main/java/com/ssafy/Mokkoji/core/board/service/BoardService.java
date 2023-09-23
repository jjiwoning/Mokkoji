package com.ssafy.Mokkoji.core.board.service;

import com.ssafy.Mokkoji.core.board.domain.Board;
import com.ssafy.Mokkoji.core.board.domain.BoardAndBoardImageSpecification;
import com.ssafy.Mokkoji.core.board.domain.BoardImage;
import com.ssafy.Mokkoji.core.board.domain.BoardSpecification;
import com.ssafy.Mokkoji.core.board.dto.request.BoardSearch;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoardService {
	
    BoardAndBoardImageSpecification getBoardDetail(Long boardId);

    List<BoardSpecification> getAllBoards(BoardSearch boardSearch);

    void deleteBoard(Long id, Long userId);

    void addBoard(Board board, Long userId, List<BoardImage> boardImages);

    void updateBoard(Long boardId, String title, String content, List<MultipartFile> images, Long userId) throws IOException;

    boolean isBoardWriter(Long userId, Long boardId);
}
