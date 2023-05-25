package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.domain.Board;
import com.ssafy.enjoytrip.domain.BoardImage;
import com.ssafy.enjoytrip.domain.User;
import com.ssafy.enjoytrip.dto.request.BoardSearch;
import com.ssafy.enjoytrip.util.FileStore;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    FileStore fileStore;

    @Autowired
    EntityManager em;

    Long userId = 0L;

    @BeforeEach
    void init() {
        User user = User.builder().name("테스트유저").loginId("test").password("test1").nickname("테스트유저").build();
        em.persist(user);
        em.flush();
        userId = user.getUserId();
    }

    @Test
    @DisplayName("게시글 등록 및 조회 테스트")
    @Transactional
    void test1() throws IOException {
        // given
        MockMultipartFile multipartFile = new MockMultipartFile("test", "test", "image/png", "hello".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile multipartFile2 = new MockMultipartFile("test2", "test2", "image/png", "hello".getBytes(StandardCharsets.UTF_8));

        List<MultipartFile> files = new ArrayList<>();
        files.add(multipartFile);
        files.add(multipartFile2);

        List<BoardImage> boardImages = fileStore.storeImages(files);

        Board board = Board.builder().title("테스트1").content("테스트1").build();
        boardService.addBoard(board, userId, boardImages);
        em.flush();

        // when
        Board board1 = boardService.getBoardDetail(board.getBoardId());

        // then
        Assertions.assertThat(board1.getUser().getName()).isEqualTo("테스트유저");
        Assertions.assertThat(board1.getCreatedDate()).isNotNull();
    }

    @Test
    @DisplayName("게시글 목록 조회 테스트")
    @Transactional
    void test2() throws IOException {

        MockMultipartFile multipartFile = new MockMultipartFile("test", "test", "image/png", "hello".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile multipartFile2 = new MockMultipartFile("test2", "test2", "image/png", "hello".getBytes(StandardCharsets.UTF_8));

        List<MultipartFile> files = new ArrayList<>();
        files.add(multipartFile);
        files.add(multipartFile2);

        List<BoardImage> boardImages = fileStore.storeImages(files);
        List<BoardImage> boardImages2 = fileStore.storeImages(files);
        List<BoardImage> boardImages3 = fileStore.storeImages(files);
        List<BoardImage> boardImages4 = fileStore.storeImages(files);

        Board board = Board.builder().title("테스트1").content("테스트1").build();
        boardService.addBoard(board, userId, boardImages);
        Board board1 = Board.builder().title("테스트2").content("테스트2").build();
        boardService.addBoard(board1, userId, boardImages2);
        Board board2 = Board.builder().title("테스트3").content("테스트3").build();
        boardService.addBoard(board2, userId, boardImages3);
        Board board3 = Board.builder().title("테스트4").content("테스트4").build();
        boardService.addBoard(board3, userId, boardImages4);

        em.flush();

        List<Board> allBoards1 = boardService.getAllBoards(BoardSearch.builder().searchString("3").build());
        Assertions.assertThat(allBoards1.size()).isEqualTo(1);

        List<Board> allBoards2 = boardService.getAllBoards(BoardSearch.builder().build());
        Assertions.assertThat(allBoards2.size()).isEqualTo(4);
    }
    
}