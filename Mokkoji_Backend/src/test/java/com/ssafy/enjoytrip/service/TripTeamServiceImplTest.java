package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.domain.TripTeam;
import com.ssafy.enjoytrip.domain.User;
import com.ssafy.enjoytrip.domain.UserTripTeam;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TripTeamServiceImplTest {

    @Autowired
    private TripTeamService tripTeamService;
    @Autowired
    private EntityManager em;

    private Long userId;

    private Long tripTeamId;

    @BeforeEach
    void before() {
        User user = User.builder().name("테스트").build();
        TripTeam tripTeam = TripTeam.builder().teamName("테스트팀").build();
        em.persist(user);
        em.persist(tripTeam);
        userId = user.getUserId();
        tripTeamId = tripTeam.getTripTeamId();
        em.flush();
    }

    @Test
    @DisplayName("팀 생성 테스트")
    @Transactional
    void test1() {
        tripTeamService.makeTripTeam(userId, "테스트팀1");
    }

    @Test
    @DisplayName("유저 초대 테스트")
    @Transactional
    void test2() {
        User inviteUser = User.builder().name("초대받을유저").build();
        em.persist(inviteUser);
        TripTeam tripTeam = tripTeamService.findTripTeam(tripTeamId);
        Long inviteUserId = inviteUser.getUserId();
        em.flush();

        tripTeamService.inviteUser(userId, inviteUserId, tripTeamId);

        TripTeam tripTeam2 = tripTeamService.findTripTeam(tripTeamId);
        for (UserTripTeam team : tripTeam2.getUserTripTeams()) {
            Assertions.assertThat(team.getUserTripTeamId()).isNotNull();
        }
    }
}