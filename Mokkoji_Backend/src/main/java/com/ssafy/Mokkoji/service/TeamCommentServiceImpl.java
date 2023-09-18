package com.ssafy.Mokkoji.service;

import com.ssafy.Mokkoji.domain.TeamBoard;
import com.ssafy.Mokkoji.domain.TeamComment;
import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.exception.NotFoundException;
import com.ssafy.Mokkoji.repository.TeamBoardRepository;
import com.ssafy.Mokkoji.repository.TeamCommentRepository;
import com.ssafy.Mokkoji.core.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeamCommentServiceImpl implements TeamCommentService {

    private final TeamCommentRepository teamCommentRepository;

    private final TeamBoardRepository teamBoardRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TeamComment> getAllTeamComment(Long teamBoardId) {
        return teamCommentRepository.getAllTeamCommentByBoardId(teamBoardId);
    }

    @Override
    public void addTeamComment(String content, Long teamBoardId, Long userId) {
        TeamBoard board = teamBoardRepository.findById(teamBoardId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));

        teamCommentRepository.save(TeamComment.builder().content(content).teamBoard(board).user(user).build());
    }

    @Override
    @Transactional(readOnly = true)
    public TeamComment getTeamComment(Long teamCommentId) {
        return teamCommentRepository.findTeamCommentByIdUsingFetchJoin(teamCommentId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
    }

    @Override
    public void editTeamComment(Long teamCommentId, String content, Long userId) {
        TeamComment teamComment = teamCommentRepository.findTeamCommentByIdUsingFetchJoin(teamCommentId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));

        if (!teamComment.getUser().isSameUser(userId)) {
            throw new IllegalArgumentException("잘못된 접근입니다");
        }

        teamComment.editComment(content);
    }

    @Override
    public void deleteTeamComment(Long teamCommentId, Long userId) {
        TeamComment teamComment = teamCommentRepository.findTeamCommentByIdUsingFetchJoin(teamCommentId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));

        if (!teamComment.getUser().isSameUser(userId)) {
            throw new IllegalArgumentException("잘못된 접근입니다");
        }

        teamCommentRepository.delete(teamComment);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isTeamCommentWriter(Long userId, Long teamCommentId) {
        return teamCommentRepository.isTeamCommentWriter(userId, teamCommentId);
    }

}