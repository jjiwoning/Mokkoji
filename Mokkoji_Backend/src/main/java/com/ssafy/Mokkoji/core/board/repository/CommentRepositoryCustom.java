package com.ssafy.Mokkoji.core.board.repository;

import com.ssafy.Mokkoji.core.board.domain.CommentSpecification;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryCustom {

    List<CommentSpecification> getAllCommentByBoardId(Long boardId);


    Optional<CommentSpecification> findCommentByIdUsingFetchJoin(Long commentId);

    void deleteCommentByBoardId(Long boardId);

    boolean isCommentWriter(Long userId, Long commentId);
}
