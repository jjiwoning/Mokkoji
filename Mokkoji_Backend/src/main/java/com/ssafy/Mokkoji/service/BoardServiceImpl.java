package com.ssafy.Mokkoji.service;

import com.ssafy.Mokkoji.domain.Board;
import com.ssafy.Mokkoji.domain.BoardImage;
import com.ssafy.Mokkoji.domain.User;
import com.ssafy.Mokkoji.dto.request.BoardSearch;
import com.ssafy.Mokkoji.exception.NotFoundException;
import com.ssafy.Mokkoji.util.FileStore;
import com.ssafy.Mokkoji.repository.BoardImageRepository;
import com.ssafy.Mokkoji.repository.BoardRepository;
import com.ssafy.Mokkoji.repository.CommentRepository;
import com.ssafy.Mokkoji.repository.UserRepository;
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
    public Board getBoardDetail(final Long boardId) {
        return boardRepository.findBoardByBoardId(boardId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> getAllBoards(final BoardSearch boardSearch) {
        return boardRepository.searchAllBoard(boardSearch);
    }

    @Override
    public void deleteBoard(final Long id, final Long userId) {
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
    public void addBoard(
            final Board board,
            final Long userId,
            final List<BoardImage> boardImages
    ) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));

        board.setUser(findUser);
        for (BoardImage boardImage : boardImages) {
            board.addImage(boardImage);
        }

        boardRepository.save(board);
    }

    @Override
    public void updateBoard(
            final Long boardId,
            final String title,
            final String content,
            final List<BoardImage> boardImages
    ) {
        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
        for (BoardImage boardImage : findBoard.getBoardImages()) {
            fileStore.deleteFile(boardImage.getStoredFileName());
            boardImageRepository.delete(boardImage);
        }
        findBoard.updateBoard(title, content, boardImages);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isBoardWriter(final Long userId, final Long boardId) {
        return boardRepository.isBoardWriter(userId, boardId);
    }
}
