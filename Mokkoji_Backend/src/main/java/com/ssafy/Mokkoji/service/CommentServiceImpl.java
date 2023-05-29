package com.ssafy.Mokkoji.service;

import com.ssafy.Mokkoji.domain.Board;
import com.ssafy.Mokkoji.domain.Comment;
import com.ssafy.Mokkoji.domain.User;
import com.ssafy.Mokkoji.exception.NotFoundException;
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
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllComment(Long boardId) {
        return commentRepository.getAllCommentByBoardId(boardId);
    }

    @Override
    public void addComment(String content, Long boardId, Long userId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
        Comment comment = Comment.builder().content(content).board(board).user(user).build();
        commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getComment(Long commentId) {
        return commentRepository.findCommentByIdUsingFetchJoin(commentId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
    }

    @Override
    public void editComment(Long commentId, String content, Long userId) {
        Comment comment = commentRepository.findCommentByIdUsingFetchJoin(commentId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
        if (!comment.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("잘못된 접근입니다");
        }
        comment.editComment(content);
    }

    @Override
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findCommentByIdUsingFetchJoin(commentId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
        if (!comment.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("잘못된 접근입니다");
        }
        commentRepository.delete(comment);
    }

    @Override
    public boolean isCommentWriter(Long userId, Long commentId) {
        return commentRepository.isCommentWriter(userId, commentId);
    }
}
