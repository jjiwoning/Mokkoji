package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryCustom {

    List<Comment> getAllCommentByBoardId(Long boardId);


    Optional<Comment> findCommentByIdUsingFetchJoin(Long commentId);

    void deleteCommentByBoardId(Long boardId);

    boolean isCommentWriter(Long userId, Long commentId);
}
