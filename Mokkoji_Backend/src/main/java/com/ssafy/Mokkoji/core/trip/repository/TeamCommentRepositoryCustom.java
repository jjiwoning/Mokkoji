package com.ssafy.Mokkoji.core.trip.repository;

import com.ssafy.Mokkoji.core.trip.domain.TeamBoard;
import com.ssafy.Mokkoji.core.trip.domain.TeamCommentSpecification;

import java.util.List;
import java.util.Optional;

public interface TeamCommentRepositoryCustom {

    List<TeamCommentSpecification> getAllTeamCommentByBoardId(Long teamBoardId);

    void deleteTeamCommentByBoardId(Long teamBoardId);

    boolean isTeamCommentWriter(Long userId, Long teamCommentId);

    void deleteAllCommentByTeam(List<TeamBoard> teamBoards);
}
