package com.ssafy.Mokkoji.repository;

import com.ssafy.Mokkoji.domain.TeamBoard;
import com.ssafy.Mokkoji.domain.TeamComment;

import java.util.List;
import java.util.Optional;

public interface TeamCommentRepositoryCustom {
    List<TeamComment> getAllTeamCommentByBoardId(Long teamBoardId);

    Optional<TeamComment> findTeamCommentByIdUsingFetchJoin(Long teamCommentId);

    void deleteTeamCommentByBoardId(Long teamBoardId);

    boolean isTeamCommentWriter(Long userId, Long teamCommentId);

    void deleteAllCommentByTeam(List<TeamBoard> teamBoards);
}
