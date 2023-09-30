package com.ssafy.Mokkoji.core.trip.service;

import com.ssafy.Mokkoji.core.trip.domain.TeamBoard;
import com.ssafy.Mokkoji.core.trip.domain.TeamBoardSpecification;
import com.ssafy.Mokkoji.core.trip.domain.TripTeam;
import com.ssafy.Mokkoji.core.trip.dto.request.TeamBoardAddRequest;
import com.ssafy.Mokkoji.core.trip.dto.response.TeamBoardDetailResponse;
import com.ssafy.Mokkoji.core.trip.dto.response.TeamBoardListResponse;
import com.ssafy.Mokkoji.core.trip.repository.TripTeamRepository;
import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.core.board.dto.request.BoardSearch;
import com.ssafy.Mokkoji.global.exception.NotFoundException;
import com.ssafy.Mokkoji.core.trip.repository.TeamBoardRepository;
import com.ssafy.Mokkoji.core.trip.repository.TeamCommentRepository;
import com.ssafy.Mokkoji.core.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeamBoardServiceImpl implements TeamBoardService {

    private final TeamBoardRepository teamBoardRepository;

    private final TeamCommentRepository teamCommentRepository;

    private final TripTeamRepository tripTeamRepository;

    @Override
    @Transactional(readOnly = true)
    public TeamBoardDetailResponse getTeamBoardDetail(final Long teamBoardId) {
        return new TeamBoardDetailResponse(getTeamBoardSpecification(teamBoardId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamBoardListResponse> getAllTeamBoards(
            final BoardSearch boardSearch,
            final Long tripTeamId
    ) {
        return teamBoardRepository.searchAllTeamBoard(boardSearch, tripTeamId).stream()
                .map(TeamBoardListResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTeamBoard(final Long id, final Long userId) {
        if (!teamBoardRepository.isTeamBoardWriter(userId, id)) {
            throw new IllegalArgumentException("잘못된 접근입니다");
        }

        teamCommentRepository.deleteTeamCommentByBoardId(id);
        teamBoardRepository.deleteById(id);
    }

    @Override
    public void addTeamBoard(
            final TeamBoardAddRequest request,
            final Long tripTeamId,
            final Long userId
    ) {
        TripTeam tripTeam = tripTeamRepository.findById(tripTeamId)
                .orElseThrow(() -> new IllegalArgumentException());

        TeamBoard teamBoard = TeamBoard.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .tripTeam(tripTeam)
                .userId(userId)
                .build();

        teamBoardRepository.save(teamBoard);
    }

    @Override
    public void updateTeamBoard(
            final Long boardId,
            final String title,
            final String content,
            final Long userId
    ) {
        TeamBoard board = getTeamBoardById(boardId);

        if (!board.getUserId().equals(userId)) {
            throw new IllegalArgumentException();
        }

        board.updateBoard(title, content);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isTeamBoardWriter(
            final Long userId,
            final Long teamBoardId
    ) {
        return teamBoardRepository.isTeamBoardWriter(userId, teamBoardId);
    }

    private TeamBoardSpecification getTeamBoardSpecification(final Long teamBoardId) {
        return teamBoardRepository.findTeamBoardByTeamBoardId(teamBoardId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
    }

    private TeamBoard getTeamBoardById(final Long boardId) {
        return teamBoardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
    }

}
