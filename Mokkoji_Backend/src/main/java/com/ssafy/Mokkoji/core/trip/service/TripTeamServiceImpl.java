package com.ssafy.Mokkoji.core.trip.service;

import com.ssafy.Mokkoji.core.trip.dto.request.TripTeamUpdateRequest;
import com.ssafy.Mokkoji.core.trip.dto.request.UserInviteRequest;
import com.ssafy.Mokkoji.core.trip.repository.*;
import com.ssafy.Mokkoji.core.user.repository.UserRepository;
import com.ssafy.Mokkoji.core.trip.domain.TeamBoard;
import com.ssafy.Mokkoji.core.trip.domain.TripTeam;
import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.core.trip.domain.UserTripTeam;
import com.ssafy.Mokkoji.core.trip.domain.TeamRole;
import com.ssafy.Mokkoji.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TripTeamServiceImpl implements TripTeamService {

    private final TripTeamRepository tripTeamRepository;

    private final UserRepository userRepository;

    private final UserTripTeamRepository userTripTeamRepository;

    private final TripPlanRepository tripPlanRepository;

    private final TeamBoardRepository teamBoardRepository;

    private final TeamCommentRepository teamCommentRepository;

    @Override
    public void makeTripTeam(
            final Long userId,
            final String teamName
    ) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("유효하지 않은 사용자"));

        TripTeam tripTeam = TripTeam.builder()
                .teamName(teamName)
                .build();

        UserTripTeam userTripTeam = UserTripTeam.builder()
                .tripTeam(tripTeam)
                .user(findUser)
                .teamRole(TeamRole.LEADER)
                .accepted(true)
                .build();

        tripTeam.addUserTripTeam(userTripTeam);

        tripTeamRepository.save(tripTeam);
    }

    @Override
    public void editTripTeam(
            final Long userId,
            final Long tripTeamId,
            final TripTeamUpdateRequest request
    ) {
        getUserTripTeamAndValidLeader(userId, tripTeamId);

        TripTeam tripTeam = tripTeamRepository.findById(tripTeamId)
                .orElseThrow(() -> new NotFoundException("잘못된 팀 입력"));

        tripTeam.editTeamName(request.getTeamName());

    }

    @Override
    public void deleteTripTeam(
            final Long tripTeamId,
            final Long userId
    ) {
        getUserTripTeamAndValidLeader(userId, tripTeamId);
        List<TeamBoard> allBoards = teamBoardRepository.findAllTeamBoardByTripTeam(tripTeamId);
        teamCommentRepository.deleteAllCommentByTeam(allBoards);
        teamBoardRepository.deleteTeamBoardByTeamId(tripTeamId);
        userTripTeamRepository.deleteUserTripTeamByTripTeamId(tripTeamId);
        tripPlanRepository.deleteTripPlanByTripTeamId(tripTeamId);
        tripTeamRepository.deleteById(tripTeamId);
    }

    @Override
    public TripTeam findTripTeam(final Long tripTeamId) {
        return tripTeamRepository.getTripTeamByIdUsingJoin(tripTeamId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
    }

    @Override
    public void inviteUser(
            final Long leaderId,
            final UserInviteRequest request
    ) {

        if (userTripTeamRepository.existsByUserIdAndTeamId(request.getUserId(), request.getTeamId())) {
            throw new IllegalArgumentException("이미 초대한 회원입니다.");
        }

        UserTripTeam userTripTeamLeader = getUserTripTeamAndValidLeader(leaderId, request.getTeamId());

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("유효하지 않은 사용자"));

        UserTripTeam userTripTeam = UserTripTeam.builder()
                .tripTeam(userTripTeamLeader.getTripTeam())
                .user(user)
                .teamRole(TeamRole.MEMBER)
                .accepted(false)
                .build();

        userTripTeamLeader.getTripTeam().addUserTripTeam(userTripTeam);
    }

    @Override
    public void acceptInvite(
            final Long userTripTeamId,
            final Long userId,
            final Long teamId
    ) {
        UserTripTeam userTripTeam = userTripTeamRepository.findUserTripTeamByIdAndUserAndTeam(userTripTeamId, userId, teamId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));

        if (userTripTeam.isAccepted()) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }

        userTripTeam.acceptInvite();
    }

    @Override
    public void refuseInvite(
            final Long userTripTeamId,
            final Long userId,
            final Long teamId
    ) {
        UserTripTeam userTripTeam = userTripTeamRepository.findUserTripTeamByIdAndUserAndTeam(userTripTeamId, userId, teamId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));

        if (userTripTeam.isAccepted()) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }

        userTripTeamRepository.delete(userTripTeam);
    }

    @Override
    @Transactional(readOnly = true)
    public UserTripTeam getUserTripTeam(final Long userTripTeamId) {
        return userTripTeamRepository.findByUserTripTeamIdJoinTripTeam(userTripTeamId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserTripTeam> getAllUserTripTeam(final Long userId) {
        return userTripTeamRepository.findAllUserTripTeamByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validUserIsLeader(
            final Long userId,
            final Long tripTeamId
    ) {
        return userTripTeamRepository.isUserLeader(userId, tripTeamId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TripTeam> getAllTripTeamByUserId(final Long userId) {
        return tripTeamRepository.findTripTeamListByUserId(userId);
    }

    private UserTripTeam getUserTripTeamAndValidLeader(
            final Long userId,
            final Long tripTeamId
    ) {
        UserTripTeam userTripTeam = userTripTeamRepository.getUserTripTeamByUserIdAndTeamId(userId, tripTeamId)
                .orElseThrow(() -> new NotFoundException("유효하지 않은 입력"));

        if (!userTripTeam.isLeader()) {
            throw new IllegalArgumentException("유효하지 않은 입력");
        }

        return userTripTeam;
    }
}
