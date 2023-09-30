package com.ssafy.Mokkoji.core.trip.service;

import com.ssafy.Mokkoji.core.trip.domain.TeamBoard;
import com.ssafy.Mokkoji.core.board.dto.request.BoardSearch;
import com.ssafy.Mokkoji.core.trip.dto.request.TeamBoardAddRequest;
import com.ssafy.Mokkoji.core.trip.dto.response.TeamBoardDetailResponse;
import com.ssafy.Mokkoji.core.trip.dto.response.TeamBoardListResponse;

import java.util.List;

public interface TeamBoardService {

    TeamBoardDetailResponse getTeamBoardDetail(Long teamBoardId);

    List<TeamBoardListResponse> getAllTeamBoards(BoardSearch boardSearch, Long tripTeamId);

    void deleteTeamBoard(Long id, Long userId);

    void addTeamBoard(TeamBoardAddRequest request, Long tripTeamId, Long userId);

    void updateTeamBoard(Long boardId, String title, String content, Long userId);

    boolean isTeamBoardWriter(Long userId, Long teamBoardId);

}
