package com.ssafy.Mokkoji.service;

import com.ssafy.Mokkoji.domain.TeamComment;

import java.util.List;

public interface TeamCommentService {

    List<TeamComment> getAllTeamComment(Long teamBoardId);

    void addTeamComment(String content, Long teamBoardId, Long userId);

    TeamComment getTeamComment(Long teamCommentId);

    void editTeamComment(Long teamCommentId, String content, Long userId);

    void deleteTeamComment(Long teamCommentId, Long userId);

    boolean isTeamCommentWriter(Long userId, Long teamCommentId);

}
