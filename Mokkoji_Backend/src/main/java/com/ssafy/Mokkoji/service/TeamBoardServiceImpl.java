package com.ssafy.Mokkoji.service;

import com.ssafy.Mokkoji.domain.TeamBoard;
import com.ssafy.Mokkoji.domain.User;
import com.ssafy.Mokkoji.dto.request.BoardSearch;
import com.ssafy.Mokkoji.exception.NotFoundException;
import com.ssafy.Mokkoji.repository.TeamBoardRepository;
import com.ssafy.Mokkoji.repository.TeamCommentRepository;
import com.ssafy.Mokkoji.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeamBoardServiceImpl implements TeamBoardService {

    private final TeamBoardRepository teamBoardRepository;

    private final UserRepository userRepository;

    private final TeamCommentRepository teamCommentRepository;

    @Override
    @Transactional(readOnly = true)
    public TeamBoard getTeamBoardDetail(Long teamBoardId) {
        return teamBoardRepository.findTeamBoardByTeamBoardId(teamBoardId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamBoard> getAllTeamBoards(BoardSearch boardSearch, Long tripTeamId) {
        return teamBoardRepository.searchAllTeamBoard(boardSearch, tripTeamId);
    }

    @Override
    public void deleteTeamBoard(Long id, Long userId) {
        TeamBoard teamBoard = teamBoardRepository.findTeamBoardByTeamBoardId(id)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));

        if (!teamBoard.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("잘못된 접근입니다");
        }

        teamCommentRepository.deleteTeamCommentByBoardId(id);
        teamBoardRepository.deleteById(id);
    }

    @Override
    public void addTeamBoard(TeamBoard teamBoard, Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));

        teamBoard.setUser(findUser);

        teamBoardRepository.save(teamBoard);
    }

    @Override
    public void updateTeamBoard(Long boardId, String title, String content) {
        TeamBoard findBoard = teamBoardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));

        findBoard.updateBoard(title, content);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isTeamBoardWriter(Long userId, Long teamBoardId) {
        return teamBoardRepository.isTeamBoardWriter(userId, teamBoardId);
    }

}
