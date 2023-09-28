package com.ssafy.Mokkoji.core.board.domain;

import com.ssafy.Mokkoji.core.model.BaseTimeEntity;
import com.ssafy.Mokkoji.core.model.Content;
import com.ssafy.Mokkoji.core.model.Title;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Embedded
    private Content content;

    @Embedded
    private Title title;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardImage> boardImages = new ArrayList<>();

    @Builder
    private Board(
            final Long boardId,
            final Content content,
            final Title title,
            final Long userId,
            final List<BoardImage> boardImages
    ) {
        this.boardId = boardId;
        this.content = content;
        this.title = title;
        this.userId = userId;
        this.boardImages = boardImages;
    }

    public static Board of(
            final String content,
            final String title,
            final Long userId
    ) {
        return Board.builder()
                .content(Content.from(content))
                .title(Title.from(title))
                .userId(userId)
                .build();
    }

    public void updateBoard(
            final String title,
            final String content,
            final List<BoardImage> boardImages
    ) {
        this.title = Title.from(title);
        this.content = Content.from(content);
        this.boardImages = new ArrayList<>();
        boardImages.forEach(this::addImage);
    }

    public boolean isUsersBoard(final Long userId) {
        return Objects.equals(userId, this.userId);
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
