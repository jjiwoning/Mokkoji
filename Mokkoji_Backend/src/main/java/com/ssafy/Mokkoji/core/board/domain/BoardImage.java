package com.ssafy.Mokkoji.core.board.domain;

import com.ssafy.Mokkoji.core.model.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Table(name = "board_images")
@Entity
@Getter
@NoArgsConstructor
public class BoardImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardImageId;

    private String userFileName; // 유저가 저장하는 이름

    private String storedFileName; // db에 저장할 파일 이름

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public BoardImage(
            final Long boardImageId,
            final String userFileName,
            final String storedFileName,
            final Board board
    ) {
        this.boardImageId = boardImageId;
        this.userFileName = userFileName;
        this.storedFileName = storedFileName;
        this.board = board;
    }

    void addBoard(final Board board) {
        this.board = board;
    }
}
