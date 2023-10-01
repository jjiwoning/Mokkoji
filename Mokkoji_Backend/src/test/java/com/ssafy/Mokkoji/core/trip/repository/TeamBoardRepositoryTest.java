package com.ssafy.Mokkoji.core.trip.repository;

import com.ssafy.Mokkoji.core.board.dto.request.BoardSearch;
import com.ssafy.Mokkoji.core.trip.domain.TeamBoard;
import com.ssafy.Mokkoji.core.trip.domain.TeamBoardSpecification;
import com.ssafy.Mokkoji.core.trip.domain.TripTeam;
import com.ssafy.Mokkoji.core.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TeamBoardRepository 단위 테스트")
@DataJpaTest
class TeamBoardRepositoryTest {

    @Autowired
    private TeamBoardRepository teamBoardRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("검색 조건을 바탕으로 게시글을 검색할 수 있다.")
    void search() {
        // given
        User user = User.builder().build();
        entityManager.persist(user);

        TripTeam tripTeam = TripTeam.builder().build();
        entityManager.persist(tripTeam);

        TeamBoard board1 = TeamBoard.builder()
                .tripTeam(tripTeam)
                .content("hello1")
                .title("hello1")
                .userId(user.getUserId())
                .build();
        TeamBoard board2 = TeamBoard.builder()
                .tripTeam(tripTeam)
                .content("hello2")
                .title("hello2")
                .userId(user.getUserId())
                .build();
        TeamBoard board3 = TeamBoard.builder()
                .tripTeam(tripTeam)
                .content("hello12")
                .title("hello12")
                .userId(user.getUserId())
                .build();
        TeamBoard board4 = TeamBoard.builder()
                .tripTeam(tripTeam)
                .content("hello33")
                .title("hello33")
                .userId(user.getUserId())
                .build();
        TeamBoard board5 = TeamBoard.builder()
                .tripTeam(tripTeam)
                .content("hello123")
                .title("hello123")
                .userId(user.getUserId())
                .build();

        entityManager.persist(board1);
        entityManager.persist(board2);
        entityManager.persist(board3);
        entityManager.persist(board4);
        entityManager.persist(board5);

        // when
        BoardSearch boardSearch = BoardSearch.builder().searchString("1").build();
        List<TeamBoardSpecification> result = teamBoardRepository.searchAllTeamBoard(boardSearch, tripTeam.getTripTeamId());

        // then
        assertThat(result).hasSize(3);
    }

    @Test
    @DisplayName("팀 게시글을 상세 조회할 수 있다.")
    void find() {
        // given
        User user = User.builder().nickname("test").build();
        entityManager.persist(user);

        TripTeam tripTeam = TripTeam.builder().build();
        entityManager.persist(tripTeam);

        TeamBoard board = TeamBoard.builder()
                .tripTeam(tripTeam)
                .content("hello1")
                .title("hello1")
                .userId(user.getUserId())
                .build();
        entityManager.persist(board);

        // when
        TeamBoardSpecification result = teamBoardRepository.findTeamBoardByTeamBoardId(board.getTeamBoardId()).get();

        // then
        assertThat(result).extracting(TeamBoardSpecification::getContent).isEqualTo("hello1");
        assertThat(result).extracting(TeamBoardSpecification::getTitle).isEqualTo("hello1");
        assertThat(result).extracting(TeamBoardSpecification::getNickname).isEqualTo("test");
    }

    @Test
    @DisplayName("게시글의 작성자이면 true를 반환한다.")
    void isWriter() {
        // given
        User user = User.builder().nickname("test").build();
        entityManager.persist(user);

        TripTeam tripTeam = TripTeam.builder().build();
        entityManager.persist(tripTeam);

        TeamBoard board = TeamBoard.builder()
                .tripTeam(tripTeam)
                .content("hello1")
                .title("hello1")
                .userId(user.getUserId())
                .build();
        entityManager.persist(board);

        // when, then
        assertThat(teamBoardRepository.isTeamBoardWriter(user.getUserId(), board.getTeamBoardId())).isTrue();
    }

    @Test
    @DisplayName("게시글의 작성자가 아니면 false를 반환한다.")
    void isNotWriter() {
        // given
        User user = User.builder().nickname("test").build();
        entityManager.persist(user);

        User otherUser = User.builder().nickname("test2").build();
        entityManager.persist(otherUser);

        TripTeam tripTeam = TripTeam.builder().build();
        entityManager.persist(tripTeam);

        TeamBoard board = TeamBoard.builder()
                .tripTeam(tripTeam)
                .content("hello1")
                .title("hello1")
                .userId(user.getUserId())
                .build();
        entityManager.persist(board);

        // when, then
        assertThat(teamBoardRepository.isTeamBoardWriter(otherUser.getUserId(), board.getTeamBoardId())).isFalse();
    }

    @Test
    @DisplayName("팀 게시글을 전부 삭제한다.")
    void deleteAll() {
        // given
        User user = User.builder().build();
        entityManager.persist(user);

        TripTeam tripTeam = TripTeam.builder().build();
        entityManager.persist(tripTeam);

        TeamBoard board1 = TeamBoard.builder()
                .tripTeam(tripTeam)
                .content("hello1")
                .title("hello1")
                .userId(user.getUserId())
                .build();
        TeamBoard board2 = TeamBoard.builder()
                .tripTeam(tripTeam)
                .content("hello2")
                .title("hello2")
                .userId(user.getUserId())
                .build();
        TeamBoard board3 = TeamBoard.builder()
                .tripTeam(tripTeam)
                .content("hello12")
                .title("hello12")
                .userId(user.getUserId())
                .build();
        TeamBoard board4 = TeamBoard.builder()
                .tripTeam(tripTeam)
                .content("hello33")
                .title("hello33")
                .userId(user.getUserId())
                .build();
        TeamBoard board5 = TeamBoard.builder()
                .tripTeam(tripTeam)
                .content("hello123")
                .title("hello123")
                .userId(user.getUserId())
                .build();

        entityManager.persist(board1);
        entityManager.persist(board2);
        entityManager.persist(board3);
        entityManager.persist(board4);
        entityManager.persist(board5);

        // when
        teamBoardRepository.deleteTeamBoardByTeamId(tripTeam.getTripTeamId());
        entityManager.flush();
        entityManager.clear();

        // then
        assertThat(teamBoardRepository.findAll()).isEmpty();
    }

    @Test
    @DisplayName("팀 게시글을 전부 조회한다.")
    void findAll() {
        // given
        User user = User.builder().build();
        entityManager.persist(user);

        TripTeam tripTeam = TripTeam.builder().build();
        entityManager.persist(tripTeam);

        TeamBoard board1 = TeamBoard.builder()
                .tripTeam(tripTeam)
                .content("hello1")
                .title("hello1")
                .userId(user.getUserId())
                .build();
        TeamBoard board2 = TeamBoard.builder()
                .tripTeam(tripTeam)
                .content("hello2")
                .title("hello2")
                .userId(user.getUserId())
                .build();
        TeamBoard board3 = TeamBoard.builder()
                .tripTeam(tripTeam)
                .content("hello12")
                .title("hello12")
                .userId(user.getUserId())
                .build();
        TeamBoard board4 = TeamBoard.builder()
                .tripTeam(tripTeam)
                .content("hello33")
                .title("hello33")
                .userId(user.getUserId())
                .build();
        TeamBoard board5 = TeamBoard.builder()
                .tripTeam(tripTeam)
                .content("hello123")
                .title("hello123")
                .userId(user.getUserId())
                .build();

        entityManager.persist(board1);
        entityManager.persist(board2);
        entityManager.persist(board3);
        entityManager.persist(board4);
        entityManager.persist(board5);

        // when
        List<TeamBoard> result = teamBoardRepository.findAllTeamBoardByTripTeam(tripTeam.getTripTeamId());

        // then
        assertThat(result).hasSize(5);
    }
}