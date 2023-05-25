package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.domain.Board;
import com.ssafy.enjoytrip.domain.BoardImage;
import com.ssafy.enjoytrip.domain.User;
import com.ssafy.enjoytrip.dto.request.BoardSearch;
import com.ssafy.enjoytrip.exception.NotFoundException;
import com.ssafy.enjoytrip.util.FileStore;
import com.ssafy.enjoytrip.repository.BoardImageRepository;
import com.ssafy.enjoytrip.repository.BoardRepository;
import com.ssafy.enjoytrip.repository.CommentRepository;
import com.ssafy.enjoytrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    private final CommentRepository commentRepository;

    private final BoardImageRepository boardImageRepository;

    private final FileStore fileStore;

    @Override
    @Transactional(readOnly = true)
    public Board getBoardDetail(Long boardId) {
        return boardRepository.findBoardByBoardId(boardId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> getAllBoards(BoardSearch boardSearch) {
        return boardRepository.searchAllBoard(boardSearch);
    }

    @Override
    public void deleteBoard(Long id, Long userId) {
        Board board = boardRepository.findBoardByBoardId(id)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));

        if (!board.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("잘못된 접근입니다");
        }

        for (BoardImage boardImage : board.getBoardImages()) {
            fileStore.deleteFile(boardImage.getStoredFileName());
            boardImageRepository.delete(boardImage);
        }

        commentRepository.deleteCommentByBoardId(id);

        boardRepository.deleteById(id);
    }

    @Override
    public void addBoard(Board board, Long userId, List<BoardImage> boardImages) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));

        board.setUser(findUser);
        for (BoardImage boardImage : boardImages) {
            board.addImage(boardImage);
        }

        boardRepository.save(board);
    }

    @Override
    public void updateBoard(Long boardId, String title, String content, List<BoardImage> boardImages) {
        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
        for (BoardImage boardImage : findBoard.getBoardImages()) {
            fileStore.deleteFile(boardImage.getStoredFileName());
            boardImageRepository.delete(boardImage);
        }
        findBoard.updateBoard(title, content, boardImages);
    }

    @Override
    public boolean isBoardWriter(Long userId, Long boardId) {
        return boardRepository.isBoardWriter(userId, boardId);
    }
}
