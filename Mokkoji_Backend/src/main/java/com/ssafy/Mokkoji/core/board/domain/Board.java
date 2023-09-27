package com.ssafy.Mokkoji.core.board.domain;

import com.ssafy.Mokkoji.core.model.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String content;

    private String title;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardImage> boardImages = new ArrayList<>();

    @Builder
    public Board(
            final Long boardId,
            final String content,
            final String title,
            final Long userId
    ) {
        this.boardId = boardId;
        this.content = content;
        this.title = title;
        this.userId = userId;
    }

    public void updateBoard(
            final String title,
            final String content,
            final List<BoardImage> boardImages
    ) {
        this.title = title;
        this.content = content;
        this.boardImages = new ArrayList<>();
        boardImages.forEach(this::addImage);
    }

    /**
     * 연관 관계 편의 메서드
     */
    public void addImage(final BoardImage boardImage) {
        boardImages.add(boardImage);
        boardImage.addBoard(this);
    }

    public void addImages(final List<BoardImage> boardImages) {
        boardImages.forEach(boardImage -> {
            this.boardImages.add(boardImage);
            boardImage.addBoard(this);
        });
    }
}
