package com.ssafy.Mokkoji.core.board.repository;

import com.ssafy.Mokkoji.core.board.domain.Board;
import com.ssafy.Mokkoji.core.board.domain.BoardAndBoardImageSpecification;
import com.ssafy.Mokkoji.core.board.domain.BoardImage;
import com.ssafy.Mokkoji.core.board.domain.BoardSpecification;
import com.ssafy.Mokkoji.core.board.dto.request.BoardSearch;
import com.ssafy.Mokkoji.core.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("BoardRepository 단위 테스트")
@DataJpaTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("검색 조건을 바탕으로 게시글을 검색한다.")
    void search() {
        // given
        User user = User.builder().build();

        entityManager.persist(user);

        Board board1 = Board.builder().title("test1").userId(user.getUserId()).build();
        Board board2 = Board.builder().title("test2").userId(user.getUserId()).build();
        Board board3 = Board.builder().title("test22").userId(user.getUserId()).build();
        Board board4 = Board.builder().title("test233").userId(user.getUserId()).build();
        Board board5 = Board.builder().title("test4").userId(user.getUserId()).build();

        entityManager.persist(board1);
        entityManager.persist(board2);
        entityManager.persist(board3);
        entityManager.persist(board4);
        entityManager.persist(board5);

        // when
        BoardSearch boardSearch = BoardSearch.builder().searchString("2").build();
        List<BoardSpecification> result = boardRepository.searchAllBoard(boardSearch);

        // then
        assertThat(result).hasSize(3);
    }

    @Test
    @DisplayName("게시글을 상세 조회 할 수 있다.")
    void findBoardByIdWithImage() {
        // given
        User user = User.builder().build();
        entityManager.persist(user);

        Board board = Board.builder().title("test1").userId(user.getUserId()).build();
        entityManager.persist(board);

        BoardImage boardImage1 = BoardImage.builder().board(board).build();
        BoardImage boardImage2 = BoardImage.builder().board(board).build();
        BoardImage boardImage3 = BoardImage.builder().board(board).build();

        entityManager.persist(boardImage1);
        entityManager.persist(boardImage2);
        entityManager.persist(boardImage3);

        // when
        BoardAndBoardImageSpecification result = boardRepository.findBoardByIdWithImage(board.getBoardId()).get();

        // then
        assertThat(result).extracting(BoardAndBoardImageSpecification::getBoardId).isEqualTo(board.getBoardId());
        assertThat(result).extracting(BoardAndBoardImageSpecification::getBoardImages)
                .extracting(List::size).isEqualTo(3);
    }

    @Test
    @DisplayName("게시글의 작성자이면 true를 반환한다.")
    void isBoardWriter() {
        // given
        User user = User.builder().build();
        entityManager.persist(user);

        Board board = Board.builder().title("test1").userId(user.getUserId()).build();
        entityManager.persist(board);

        // when, then
        assertThat(boardRepository.isBoardWriter(user.getUserId(), board.getBoardId())).isTrue();
    }

    @Test
    @DisplayName("게시글의 작성자가 아니면 false를 반환한다.")
    void isNotBoardWriter() {
        // given
        User user = User.builder().build();
        User otherUser = User.builder().build();

        entityManager.persist(user);
        entityManager.persist(otherUser);

        Board board = Board.builder().title("test1").userId(user.getUserId()).build();
        entityManager.persist(board);

        // when, then
        assertThat(boardRepository.isBoardWriter(otherUser.getUserId(), board.getBoardId())).isFalse();
    }
}