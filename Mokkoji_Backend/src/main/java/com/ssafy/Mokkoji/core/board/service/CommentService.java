package com.ssafy.Mokkoji.core.board.service;

import com.ssafy.Mokkoji.core.board.dto.request.CommentRequest;
import com.ssafy.Mokkoji.core.board.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {
    List<CommentResponse> getAllComment(Long boardId);

    void addComment(CommentRequest request, Long boardId, Long userId);

    void editComment(Long commentId, CommentRequest request, Long userId);

    void deleteComment(Long commentId, Long userId);

    boolean isCommentWriter(Long userId, Long commentId);
}
