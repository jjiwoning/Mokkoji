package com.ssafy.Mokkoji.core.board.repository;

import com.ssafy.Mokkoji.core.board.domain.CommentSpecification;

import java.util.List;

public interface CommentRepositoryCustom {

    List<CommentSpecification> getAllCommentByBoardId(Long boardId);

    void deleteCommentByBoardId(Long boardId);

    boolean isCommentWriter(Long userId, Long commentId);
}
