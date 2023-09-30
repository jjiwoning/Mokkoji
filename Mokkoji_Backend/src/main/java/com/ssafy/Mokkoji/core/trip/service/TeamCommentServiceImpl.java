package com.ssafy.Mokkoji.core.trip.service;

import com.ssafy.Mokkoji.core.trip.domain.TeamBoard;
import com.ssafy.Mokkoji.core.trip.domain.TeamComment;
import com.ssafy.Mokkoji.core.trip.domain.TeamCommentSpecification;
import com.ssafy.Mokkoji.core.trip.dto.response.TeamCommentResponse;
import com.ssafy.Mokkoji.core.trip.repository.TeamBoardRepository;
import com.ssafy.Mokkoji.core.trip.repository.TeamCommentRepository;
import com.ssafy.Mokkoji.global.exception.NotFoundException;
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
public class TeamCommentServiceImpl implements TeamCommentService {

    private final TeamCommentRepository teamCommentRepository;

    private final TeamBoardRepository teamBoardRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TeamCommentResponse> getAllTeamComment(Long teamBoardId) {
        return teamCommentRepository.getAllTeamCommentByBoardId(teamBoardId).stream()
                .map(TeamCommentResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public void addTeamComment(String content, Long teamBoardId, Long userId) {
        TeamBoard board = teamBoardRepository.findById(teamBoardId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다"));

        teamCommentRepository.save(TeamComment.builder().content(content).teamBoard(board).userId(userId).build());
    }

    @Override
    public void editTeamComment(Long teamCommentId, String content, Long userId) {
        TeamComment teamComment = getCommentById(teamCommentId);

        if (!teamComment.getUserId().equals(userId)) {
            throw new IllegalArgumentException("잘못된 접근입니다");
        }

        teamComment.editComment(content);
    }

    @Override
    public void deleteTeamComment(Long teamCommentId, Long userId) {
        TeamComment teamComment = getCommentById(teamCommentId);

        if (!teamComment.getUserId().equals(userId)) {
            throw new IllegalArgumentException("잘못된 접근입니다");
        }

        teamCommentRepository.delete(teamComment);
    }

    private TeamComment getCommentById(Long teamCommentId) {
        return teamCommentRepository.findById(teamCommentId)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isTeamCommentWriter(Long userId, Long teamCommentId) {
        return teamCommentRepository.isTeamCommentWriter(userId, teamCommentId);
    }

}