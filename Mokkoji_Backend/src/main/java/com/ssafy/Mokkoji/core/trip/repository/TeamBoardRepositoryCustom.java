package com.ssafy.Mokkoji.core.trip.repository;

import com.ssafy.Mokkoji.core.board.dto.request.BoardSearch;
import com.ssafy.Mokkoji.core.trip.domain.TeamBoard;
import com.ssafy.Mokkoji.core.trip.domain.TeamBoardSpecification;

import java.util.List;
import java.util.Optional;

public interface TeamBoardRepositoryCustom {
    List<TeamBoardSpecification> searchAllTeamBoard(BoardSearch boardSearch, Long tripTeamId);

    Optional<TeamBoardSpecification> findTeamBoardByTeamBoardId(Long teamBoardId);

    boolean isTeamBoardWriter(Long userId, Long teamBoardId);

    void deleteTeamBoardByTeamId(Long tripTeamId);

    List<TeamBoard> findAllTeamBoardByTripTeam(Long tripTeamId);
}
