package com.ssafy.Mokkoji.core.board.service;

import com.ssafy.Mokkoji.core.board.domain.Board;
import com.ssafy.Mokkoji.core.board.domain.BoardAndBoardImageSpecification;
import com.ssafy.Mokkoji.core.board.domain.BoardImage;
import com.ssafy.Mokkoji.core.board.domain.BoardSpecification;
import com.ssafy.Mokkoji.core.board.dto.request.BoardSearch;
import com.ssafy.Mokkoji.core.board.repository.BoardImageRepository;
import com.ssafy.Mokkoji.core.board.repository.BoardRepository;
import com.ssafy.Mokkoji.core.board.repository.CommentRepository;
import com.ssafy.Mokkoji.core.user.repository.UserRepository;
import com.ssafy.Mokkoji.global.exception.NotFoundException;
import com.ssafy.Mokkoji.global.util.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    private final CommentRepository commentRepository;

    private final BoardImageRepository boardImageRepository;

    private final FileStore fileStore;

    @Override
    @Transactional(readOnly = true)
    public BoardAndBoardImageSpecification getBoardDetail(final Long boardId) {
        return getBoardByIdWithImage(boardId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BoardSpecification> getAllBoards(final BoardSearch boardSearch) {
        return boardRepository.searchAllBoard(boardSearch);
    }

    @Override
    public void deleteBoard(final Long id, final Long userId) {
        Board board = getBoardById(id);

        if (!board.getUserId().equals(userId)) {
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
            final List<MultipartFile> images,
            final Long userId
    ) throws IOException {

        List<BoardImage> boardImages = fileStore.storeImages(images);

        Board findBoard = getBoardById(boardId);

        if (!findBoard.getUserId().equals(userId)) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }

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

    private BoardAndBoardImageSpecification getBoardByIdWithImage(Long boardId) {
        return boardRepository.findBoardByIdWithImage(boardId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
    }

    private Board getBoardById(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
    }
}
