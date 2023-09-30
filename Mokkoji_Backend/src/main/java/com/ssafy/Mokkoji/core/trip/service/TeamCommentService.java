package com.ssafy.Mokkoji.core.trip.service;

import com.ssafy.Mokkoji.core.trip.dto.response.TeamCommentResponse;

import java.util.List;

public interface TeamCommentService {

    List<TeamCommentResponse> getAllTeamComment(Long teamBoardId);

    void addTeamComment(String content, Long teamBoardId, Long userId);

    void editTeamComment(Long teamCommentId, String content, Long userId);

    void deleteTeamComment(Long teamCommentId, Long userId);

    boolean isTeamCommentWriter(Long userId, Long teamCommentId);

}
