package com.ssafy.Mokkoji.core.board.service;

import com.ssafy.Mokkoji.core.board.domain.Board;
import com.ssafy.Mokkoji.core.board.domain.Comment;
import com.ssafy.Mokkoji.core.board.dto.request.CommentRequest;
import com.ssafy.Mokkoji.core.board.dto.response.CommentResponse;
import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.global.exception.NotFoundException;
import com.ssafy.Mokkoji.core.board.repository.BoardRepository;
import com.ssafy.Mokkoji.core.board.repository.CommentRepository;
import com.ssafy.Mokkoji.core.user.repository.UserRepository;
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
    private final UserRepository userRepository;

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
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));

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
            throw new IllegalArgumentException("잘못된 접근입니다");
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
            throw new IllegalArgumentException("잘못된 접근입니다");
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
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
    }
}
