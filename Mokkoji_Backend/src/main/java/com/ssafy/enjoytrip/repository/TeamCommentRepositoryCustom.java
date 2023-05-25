package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.domain.TeamBoard;
import com.ssafy.enjoytrip.domain.TeamComment;

import java.util.List;
import java.util.Optional;

public interface TeamCommentRepositoryCustom {
    List<TeamComment> getAllTeamCommentByBoardId(Long teamBoardId);

    Optional<TeamComment> findTeamCommentByIdUsingFetchJoin(Long teamCommentId);

    void deleteTeamCommentByBoardId(Long teamBoardId);

    boolean isTeamCommentWriter(Long userId, Long teamCommentId);

    void deleteAllCommentByTeam(List<TeamBoard> teamBoards);
}
