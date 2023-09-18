package com.ssafy.Mokkoji.repository;

import com.ssafy.Mokkoji.domain.TeamBoard;
import com.ssafy.Mokkoji.core.board.dto.request.BoardSearch;

import java.util.List;
import java.util.Optional;

public interface TeamBoardRepositoryCustom {
    List<TeamBoard> searchAllTeamBoard(BoardSearch boardSearch, Long tripTeamId);

    Optional<TeamBoard> findTeamBoardByTeamBoardId(Long teamBoardId);

    boolean isTeamBoardWriter(Long userId, Long teamBoardId);

    void deleteTeamBoardByTeamId(Long tripTeamId);

    List<TeamBoard> findAllTeamBoardByTripTeam(Long tripTeamId);
}
