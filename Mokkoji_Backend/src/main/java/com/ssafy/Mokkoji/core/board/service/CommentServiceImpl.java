package com.ssafy.Mokkoji.core.board.service;

import com.ssafy.Mokkoji.core.board.domain.Board;
import com.ssafy.Mokkoji.core.board.domain.Comment;
import com.ssafy.Mokkoji.core.board.dto.request.CommentRequest;
import com.ssafy.Mokkoji.core.board.dto.response.CommentResponse;
import com.ssafy.Mokkoji.core.board.exception.BoardNotFoundException;
import com.ssafy.Mokkoji.core.board.exception.CommentNotFoundException;
import com.ssafy.Mokkoji.core.board.exception.NotMyCommentException;
import com.ssafy.Mokkoji.core.board.repository.BoardRepository;
import com.ssafy.Mokkoji.core.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BoardRepository boardRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getAllComment(final Long boardId) {
        return commentRepository.getAllCommentByBoardId(boardId).stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public void addComment(
            final CommentRequest request,
            final Long boardId,
            final Long userId
    ) {
        Board board = getBoardById(boardId);

        commentRepository.save(Comment.of(request.getContent(), userId, board));
    }

    @Override
    public void editComment(
            final Long commentId,
            final CommentRequest request,
            final Long userId
    ) {
        Comment comment = getCommentById(commentId);

        if (!comment.isSameUser(userId)) {
            throw new NotMyCommentException();
        }

        comment.editComment(request.getContent());
    }

    @Override
    public void deleteComment(
            final Long commentId,
            final Long userId
    ) {
        Comment comment = getCommentById(commentId);

        if (!comment.isSameUser(userId)) {
            throw new NotMyCommentException();
        }

        commentRepository.delete(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isCommentWriter(final Long userId, final Long commentId) {
        return commentRepository.isCommentWriter(userId, commentId);
    }

    private Comment getCommentById(final Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);
    }

    private Board getBoardById(final Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);
    }
}
