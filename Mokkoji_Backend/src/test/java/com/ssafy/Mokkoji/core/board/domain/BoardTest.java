package com.ssafy.Mokkoji.core.board.domain;

import com.ssafy.Mokkoji.core.board.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

@DisplayName("Board 단위 테스트")
class BoardTest {

    @Test
    @DisplayName("Board를 수정할 수 있다.")
    void update() {
        Board board = Board.builder().build();

        board.updateBoard("테스트1", "안녕", new ArrayList<>());

        Assertions.assertThat(board).extracting(Board::getTitle).isEqualTo("테스트1");
        Assertions.assertThat(board).extracting(Board::getContent).isEqualTo("안녕");
    }
}
