package com.ssafy.Mokkoji.core.board.service;

import com.ssafy.Mokkoji.core.board.domain.Board;
import com.ssafy.Mokkoji.core.board.domain.Comment;
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
    public List<Comment> getAllComment(final Long boardId) {
        return commentRepository.getAllCommentByBoardId(boardId);
    }

    @Override
    public void addComment(
            final String content,
            final Long boardId,
            final Long userId
    ) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));

        commentRepository.save(Comment.builder().content(content).board(board).user(user).build());
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getComment(final Long commentId) {
        return getCommentById(commentId);
    }

    @Override
    public void editComment(
            final Long commentId,
            final String content,
            final Long userId
    ) {
        Comment comment = getCommentById(commentId);

        // TODO: 2023/09/17 같은 유저 판별 comment와 user 간접 참조로 바꾸고 다시 리팩토링하기
        if (!comment.getUser().isSameUser(userId)) {
            throw new IllegalArgumentException("잘못된 접근입니다");
        }

        comment.editComment(content);
    }

    private Comment getCommentById(Long commentId) {
        return commentRepository.findCommentByIdUsingFetchJoin(commentId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
    }

    @Override
    public void deleteComment(
            final Long commentId,
            final Long userId
    ) {
        Comment comment = getCommentById(commentId);

        if (!comment.getUser().isSameUser(userId)) {
            throw new IllegalArgumentException("잘못된 접근입니다");
        }

        commentRepository.delete(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isCommentWriter(Long userId, Long commentId) {
        return commentRepository.isCommentWriter(userId, commentId);
    }
}
