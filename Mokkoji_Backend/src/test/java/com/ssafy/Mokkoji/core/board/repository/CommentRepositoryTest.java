package com.ssafy.Mokkoji.core.board.repository;

import com.ssafy.Mokkoji.core.board.domain.Board;
import com.ssafy.Mokkoji.core.board.domain.Comment;
import com.ssafy.Mokkoji.core.board.domain.CommentSpecification;
import com.ssafy.Mokkoji.core.user.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("CommentRepository 단위 테스트")
@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("특정 게시판의 모든 댓글을 가져온다.")
    void getAllComment() {
        // given
        Board board = Board.builder().build();
        User user = User.builder().build();

        entityManager.persist(board);
        entityManager.persist(user);

        Comment comment1 = Comment.builder().userId(user.getUserId()).board(board).build();
        Comment comment2 = Comment.builder().userId(user.getUserId()).board(board).build();
        Comment comment3 = Comment.builder().userId(user.getUserId()).board(board).build();
        Comment comment4 = Comment.builder().userId(user.getUserId()).board(board).build();

        entityManager.persist(comment1);
        entityManager.persist(comment2);
        entityManager.persist(comment3);
        entityManager.persist(comment4);

        // when
        List<CommentSpecification> result = commentRepository.getAllCommentByBoardId(board.getBoardId());

        // then
        assertThat(result).hasSize(4);
    }

    @Test
    @DisplayName("특정 게시판의 모든 댓글을 삭제한다.")
    void deleteAll() {
        // given
        Board board = Board.builder().build();
        User user = User.builder().build();

        entityManager.persist(board);
        entityManager.persist(user);

        Comment comment1 = Comment.builder().userId(user.getUserId()).board(board).build();
        Comment comment2 = Comment.builder().userId(user.getUserId()).board(board).build();
        Comment comment3 = Comment.builder().userId(user.getUserId()).board(board).build();
        Comment comment4 = Comment.builder().userId(user.getUserId()).board(board).build();

        entityManager.persist(comment1);
        entityManager.persist(comment2);
        entityManager.persist(comment3);
        entityManager.persist(comment4);

        // when
        commentRepository.deleteCommentByBoardId(board.getBoardId());
        entityManager.flush();
        entityManager.clear();

        // then
        assertThat(commentRepository.findAll()).isEmpty();
    }

    @Test
    @DisplayName("댓글 작성자가 맞다면 true를 반환한다.")
    void isCommentWriter() {
        // given
        Board board = Board.builder().build();
        User user = User.builder().build();

        entityManager.persist(board);
        entityManager.persist(user);

        Comment comment = Comment.builder().userId(user.getUserId()).board(board).build();

        entityManager.persist(comment);

        // when, then
        assertThat(commentRepository.isCommentWriter(user.getUserId(), comment.getCommentId())).isTrue();
    }

    @Test
    @DisplayName("댓글 작성자가 아니라면 false를 반환한다.")
    void isNotCommentWriter() {
        // given
        Board board = Board.builder().build();
        User user = User.builder().build();
        User otherUser = User.builder().build();

        entityManager.persist(board);
        entityManager.persist(user);
        entityManager.persist(otherUser);

        Comment comment = Comment.builder().userId(user.getUserId()).board(board).build();

        entityManager.persist(comment);

        // when, then
        assertThat(commentRepository.isCommentWriter(otherUser.getUserId(), comment.getCommentId())).isFalse();
    }

}