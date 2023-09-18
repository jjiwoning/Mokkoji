package com.ssafy.Mokkoji.core.board.domain;

import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.domain.BaseTimeEntity;
import lombok.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardImage> boardImages = new ArrayList<>();

    @Builder
    public Board(
            final Long boardId,
            final String content,
            final String title,
            final User user
    ) {
        this.boardId = boardId;
        this.content = content;
        this.title = title;
        this.user = user;
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

    public void setUser(final User user) {
        this.user = user;
    }

    /**
     * 연관 관계 편의 메서드
     */
    public void addImage(final BoardImage boardImage) {
        boardImages.add(boardImage);
        boardImage.addBoard(this);
    }
}
