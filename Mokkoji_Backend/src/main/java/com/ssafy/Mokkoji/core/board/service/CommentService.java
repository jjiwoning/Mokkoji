package com.ssafy.Mokkoji.core.board.service;

import com.ssafy.Mokkoji.core.board.domain.Comment;
import com.ssafy.Mokkoji.core.board.domain.CommentSpecification;
import com.ssafy.Mokkoji.core.board.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {
    List<CommentResponse> getAllComment(Long boardId);

    void addComment(String content, Long boardId, Long userId);

    void editComment(Long commentId, String content, Long userId);

    void deleteComment(Long commentId, Long userId);

    boolean isCommentWriter(Long userId, Long commentId);
}
