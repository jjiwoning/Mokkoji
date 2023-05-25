package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.domain.Board;
import com.ssafy.enjoytrip.domain.Comment;
import com.ssafy.enjoytrip.domain.User;
import com.ssafy.enjoytrip.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
class CommentServiceImplTest {

    @Autowired
    EntityManager em;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentService commentService;

    private Long boardId = 0L;
    private Long userId = 0L;

    @BeforeEach
    void init() {
        User user = User.builder().loginId("test1").password("test1").build();
        Board board = Board.builder().title("테스트1").content("테스트1").build();
        boardRepository.save(board);
        em.persist(user);
        commentService.addComment("comment1", board.getBoardId(), user.getUserId());
        commentService.addComment("comment2", board.getBoardId(), user.getUserId());
        boardId = board.getBoardId();
        userId = user.getUserId();
        em.flush();
    }

    @Test
    @Transactional
    @DisplayName("게시판 댓글 조회")
    void getAllComment() {
        List<Comment> allComment = commentService.getAllComment(boardId);
        Assertions.assertThat(allComment.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    @DisplayName("댓글 등록")
    void addComment() {
        commentService.addComment("comment", boardId, userId);
        List<Comment> allComment = commentService.getAllComment(boardId);
        Assertions.assertThat(allComment.size()).isEqualTo(3);
    }

}