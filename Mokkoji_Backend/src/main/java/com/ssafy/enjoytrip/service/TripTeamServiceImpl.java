package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.domain.TeamBoard;
import com.ssafy.enjoytrip.domain.TripTeam;
import com.ssafy.enjoytrip.domain.User;
import com.ssafy.enjoytrip.domain.UserTripTeam;
import com.ssafy.enjoytrip.domain.team_relation.TeamRole;
import com.ssafy.enjoytrip.exception.NotFoundException;
import com.ssafy.enjoytrip.repository.*;
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
    public void makeTripTeam(Long userId, String teamName) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("유효하지 않은 사용자"));

        TripTeam tripTeam = TripTeam.builder().teamName(teamName).build();

        UserTripTeam userTripTeam = UserTripTeam.builder().tripTeam(tripTeam).user(findUser).teamRole(TeamRole.LEADER).accepted(true).build();

        tripTeam.addUserTripTeam(userTripTeam);

        tripTeamRepository.save(tripTeam);
    }

    @Override
    public void editTripTeam(Long userId, Long tripTeamId, String teamName) {
        getUserTripTeamAndValidLeader(userId, tripTeamId);

        TripTeam tripTeam = tripTeamRepository.findById(tripTeamId)
                .orElseThrow(() -> new NotFoundException("잘못된 팀 입력"));

        tripTeam.editTeamName(teamName);

    }

    @Override
    public void deleteTripTeam(Long tripTeamId, Long userId) {
        getUserTripTeamAndValidLeader(userId, tripTeamId);
        List<TeamBoard> allBoards = teamBoardRepository.findAllTeamBoardByTripTeam(tripTeamId);
        teamCommentRepository.deleteAllCommentByTeam(allBoards);
        teamBoardRepository.deleteTeamBoardByTeamId(tripTeamId);
        userTripTeamRepository.deleteUserTripTeamByTripTeamId(tripTeamId);
        tripPlanRepository.deleteTripPlanByTripTeamId(tripTeamId);
        tripTeamRepository.deleteById(tripTeamId);
    }

    @Override
    public TripTeam findTripTeam(Long tripTeamId) {
        return tripTeamRepository.getTripTeamByIdUsingJoin(tripTeamId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
    }

    @Override
    public void inviteUser(Long leaderId, Long userId, Long teamId) {

        if (userTripTeamRepository.existsByUserIdAndTeamId(userId, teamId)) {
            throw new IllegalArgumentException("이미 초대한 회원입니다.");
        }

        UserTripTeam userTripTeamLeader = getUserTripTeamAndValidLeader(leaderId, teamId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("유효하지 않은 사용자"));

        UserTripTeam userTripTeam = UserTripTeam.builder().tripTeam(userTripTeamLeader.getTripTeam()).user(user).teamRole(TeamRole.MEMBER).accepted(false).build();

        userTripTeamLeader.getTripTeam().addUserTripTeam(userTripTeam);
    }

    @Override
    public void acceptInvite(Long userTripTeamId, Long userId, Long teamId) {
        UserTripTeam userTripTeam = userTripTeamRepository.findUserTripTeamByIdAndUserAndTeam(userTripTeamId, userId, teamId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
        if (userTripTeam.isAccepted()) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }
        userTripTeam.acceptInvite();
    }

    @Override
    public void refuseInvite(Long userTripTeamId, Long userId, Long teamId) {
        UserTripTeam userTripTeam = userTripTeamRepository.findUserTripTeamByIdAndUserAndTeam(userTripTeamId, userId, teamId)
                .orElseThrow(() -> new NotFoundException("잘못된 접근입니다."));
        if (userTripTeam.isAccepted()) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }
        userTripTeamRepository.delete(userTripTeam);
    }

    @Override
    public UserTripTeam getUserTripTeam(Long userTripTeamId) {
        return userTripTeamRepository.findByUserTripTeamIdJoinTripTeam(userTripTeamId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다"));
    }

    @Override
    public List<UserTripTeam> getAllUserTripTeam(Long userId) {
        return userTripTeamRepository.findAllUserTripTeamByUserId(userId);
    }

    @Override
    public boolean validUserIsLeader(Long userId, Long tripTeamId) {
        return userTripTeamRepository.isUserLeader(userId, tripTeamId);
    }

    @Override
    public List<TripTeam> getAllTripTeamByUserId(Long userId) {
        return tripTeamRepository.findTripTeamListByUserId(userId);
    }

    private UserTripTeam getUserTripTeamAndValidLeader(Long userId, Long tripTeamId) {
        UserTripTeam userTripTeam = userTripTeamRepository.getUserTripTeamByUserIdAndTeamId(userId, tripTeamId)
                .orElseThrow(() -> new NotFoundException("유효하지 않은 입력"));

        // 유저 권한 체크
        if (userTripTeam.getTeamRole() != TeamRole.LEADER) {
            throw new IllegalArgumentException("유효하지 않은 입력");
        }

        return userTripTeam;
    }
}
