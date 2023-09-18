package com.ssafy.Mokkoji.service;

import com.ssafy.Mokkoji.domain.TeamBoard;
import com.ssafy.Mokkoji.core.board.dto.request.BoardSearch;

import java.util.List;

public interface TeamBoardService {

    TeamBoard getTeamBoardDetail(Long teamBoardId);

    List<TeamBoard> getAllTeamBoards(BoardSearch boardSearch, Long tripTeamId);

    void deleteTeamBoard(Long id, Long userId);

    void addTeamBoard(TeamBoard teamBoard, Long userId);

    void updateTeamBoard(Long boardId, String title, String content);

    boolean isTeamBoardWriter(Long userId, Long teamBoardId);

}
