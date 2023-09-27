package com.ssafy.Mokkoji.core.board.domain;

import com.ssafy.Mokkoji.core.model.Content;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Comment 단위 테스트")
class CommentTest {

    @Test
    @DisplayName("댓글을 수정할 수 있다.")
    void edit() {
        // given
        Comment comment = Comment.builder()
                .content(Content.from("hello"))
                .build();

        // when
        comment.editComment("안녕하세요");

        // then
        assertThat(comment).extracting(Comment::getContent).isEqualTo(Content.from("안녕하세요"));
    }

    @Test
    @DisplayName("본인의 댓글이 맞으면 true를 반환한다.")
    void isSameUser() {
        // given
        Long userId = 1L;

        Comment comment = Comment.builder()
                .content(Content.from("hello"))
                .userId(userId)
                .build();

        // when, then
        assertThat(comment.isSameUser(userId)).isTrue();
    }

    @Test
    @DisplayName("본인의 댓글이 아니면 false를 반환한다.")
    void isNotSameUser() {
        // given
        Long userId = 1L;
        Long otherUserId = 2L;

        Comment comment = Comment.builder()
                .content(Content.from("hello"))
                .userId(userId)
                .build();

        // when, then
        assertThat(comment.isSameUser(otherUserId)).isFalse();
    }
}
