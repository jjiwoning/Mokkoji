package com.ssafy.Mokkoji.core.board.domain;

import com.ssafy.Mokkoji.core.model.Content;
import com.ssafy.Mokkoji.core.model.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Board 단위 테스트")
class BoardTest {

    @Test
    @DisplayName("Board를 수정할 수 있다.")
    void update() {
        Board board = Board.builder().build();

        board.updateBoard("테스트1", "안녕", new ArrayList<>());

        assertThat(board).extracting(Board::getTitle).isEqualTo(Title.from("테스트1"));
        assertThat(board).extracting(Board::getContent).isEqualTo(Content.from("안녕"));
    }

    @Test
    @DisplayName("자신의 게시글이면 true를 반환한다.")
    void isMyBoard() {
        // given
        Long userId = 1L;
        Board board = Board.of("test1", "test1", userId);

        // when, then
        assertThat(board.isUsersBoard(userId)).isTrue();
    }

    @Test
    @DisplayName("자신의 게시글이 아니면 false를 반환한다.")
    void isNotMyBoard() {
        // given
        Long userId = 1L;
        Long otherUserId = 2L;
        Board board = Board.of("test1", "test1", userId);

        // when, then
        assertThat(board.isUsersBoard(otherUserId)).isFalse();
    }
}
