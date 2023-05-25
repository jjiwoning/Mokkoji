package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.domain.TeamBoard;
import com.ssafy.enjoytrip.dto.request.BoardSearch;

import java.util.List;
import java.util.Optional;

public interface TeamBoardRepositoryCustom {
    List<TeamBoard> searchAllTeamBoard(BoardSearch boardSearch, Long tripTeamId);

    Optional<TeamBoard> findTeamBoardByTeamBoardId(Long teamBoardId);

    boolean isTeamBoardWriter(Long userId, Long teamBoardId);

    void deleteTeamBoardByTeamId(Long tripTeamId);

    List<TeamBoard> findAllTeamBoardByTripTeam(Long tripTeamId);
}
