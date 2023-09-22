package com.ssafy.Mokkoji.core.board.service;

import com.ssafy.Mokkoji.core.board.domain.Board;
import com.ssafy.Mokkoji.core.board.domain.BoardImage;
import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.core.board.dto.request.BoardSearch;
import com.ssafy.Mokkoji.global.exception.NotFoundException;
import com.ssafy.Mokkoji.global.util.FileStore;
import com.ssafy.Mokkoji.core.board.repository.BoardImageRepository;
import com.ssafy.Mokkoji.core.board.repository.BoardRepository;
import com.ssafy.Mokkoji.core.board.repository.CommentRepository;
import com.ssafy.Mokkoji.core.user.repository.UserRepository;
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
        return getBoardByIdWithImage(boardId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> getAllBoards(final BoardSearch boardSearch) {
        return boardRepository.searchAllBoard(boardSearch);
    }

    @Override
    public void deleteBoard(final Long id, final Long userId) {
        Board board = getBoardByIdWithImage(id);

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
        User findUser = getUserById(userId);
        board.setUser(findUser); // TODO: 2023/09/19 User 간접 참조로 바꾸고 이거 로직 변경하기
        
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
        Board findBoard = getBoardById(boardId);

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

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
    }

    private Board getBoardByIdWithImage(Long boardId) {
        return boardRepository.findBoardByIdWithImage(boardId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
    }

    private Board getBoardById(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
    }
}
