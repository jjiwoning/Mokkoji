package com.ssafy.Mokkoji.core.trip.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ssafy.Mokkoji.core.trip.domain.TeamBoard;
import com.ssafy.Mokkoji.core.trip.domain.TeamComment;
import com.ssafy.Mokkoji.core.trip.domain.TeamCommentSpecification;
import com.ssafy.Mokkoji.core.user.domain.User;

import helper.domain.UserDomainHelper;

@DisplayName("TeamCommentRepository 단위 테스트")
@DataJpaTest
class TeamCommentRepositoryTest {

	@Autowired
	private TeamCommentRepository teamCommentRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	@DisplayName("게시글의 모든 댓글을 조회한다.")
	void findAll() {
		// given
		TeamBoard board = TeamBoard.builder().build();
		User user = UserDomainHelper.buildDefaultUser().build();

		entityManager.persist(board);
		entityManager.persist(user);

		TeamComment comment1 = TeamComment.builder().userId(user.getUserId()).teamBoard(board).content("test").build();
		TeamComment comment2 = TeamComment.builder().userId(user.getUserId()).teamBoard(board).content("test").build();
		TeamComment comment3 = TeamComment.builder().userId(user.getUserId()).teamBoard(board).content("test").build();
		TeamComment comment4 = TeamComment.builder().userId(user.getUserId()).teamBoard(board).content("test").build();

		entityManager.persist(comment1);
		entityManager.persist(comment2);
		entityManager.persist(comment3);
		entityManager.persist(comment4);

		// when
		List<TeamCommentSpecification> result = teamCommentRepository.getAllTeamCommentByBoardId(
			board.getTeamBoardId());

		// then
		assertThat(result).hasSize(4);
	}

	@Test
	@DisplayName("게시글의 모든 댓글을 삭제한다.")
	void deleteAll() {
		// given
		TeamBoard board = TeamBoard.builder().build();
		User user = UserDomainHelper.buildDefaultUser().build();

		entityManager.persist(board);
		entityManager.persist(user);

		TeamComment comment1 = TeamComment.builder().userId(user.getUserId()).teamBoard(board).content("test").build();
		TeamComment comment2 = TeamComment.builder().userId(user.getUserId()).teamBoard(board).content("test").build();
		TeamComment comment3 = TeamComment.builder().userId(user.getUserId()).teamBoard(board).content("test").build();
		TeamComment comment4 = TeamComment.builder().userId(user.getUserId()).teamBoard(board).content("test").build();

		entityManager.persist(comment1);
		entityManager.persist(comment2);
		entityManager.persist(comment3);
		entityManager.persist(comment4);

		// when
		teamCommentRepository.deleteTeamCommentByBoardId(board.getTeamBoardId());
		entityManager.flush();
		entityManager.clear();

		// then
		assertThat(teamCommentRepository.findAll()).isEmpty();
	}

	@Test
	@DisplayName("자신의 댓글이면 true를 반환한다.")
	void isCommentWriter() {
		// given
		TeamBoard board = TeamBoard.builder().build();
		User user = UserDomainHelper.buildDefaultUser().build();

		entityManager.persist(board);
		entityManager.persist(user);

		TeamComment comment = TeamComment.builder().userId(user.getUserId()).teamBoard(board).content("test").build();

		entityManager.persist(comment);

		// when, then
		assertThat(teamCommentRepository.isTeamCommentWriter(user.getUserId(), comment.getTeamCommentId())).isTrue();
	}

	@Test
	@DisplayName("자신의 댓글이 아니면 false를 반환한다.")
	void isNotCommentWriter() {
		// given
		TeamBoard board = TeamBoard.builder().build();
		User user = UserDomainHelper.buildDefaultUser()
			.loginId("user1")
			.build();
		User otherUser = UserDomainHelper.buildDefaultUser()
			.loginId("user2")
			.build();

		entityManager.persist(board);
		entityManager.persist(user);
		entityManager.persist(otherUser);

		TeamComment comment = TeamComment.builder().userId(user.getUserId()).teamBoard(board).content("test").build();

		entityManager.persist(comment);

		// when, then
		assertThat(
			teamCommentRepository.isTeamCommentWriter(otherUser.getUserId(), comment.getTeamCommentId())).isFalse();
	}
}