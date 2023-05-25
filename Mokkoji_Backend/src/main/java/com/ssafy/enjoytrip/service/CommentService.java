package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComment(Long boardId);

    void addComment(String content, Long boardId, Long userId);

    Comment getComment(Long commentId);

    void editComment(Long commentId, String content, Long userId);

    void deleteComment(Long commentId, Long userId);

    boolean isCommentWriter(Long userId, Long commentId);
}
