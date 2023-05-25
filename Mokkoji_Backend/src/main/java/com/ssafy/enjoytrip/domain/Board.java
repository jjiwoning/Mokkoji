package com.ssafy.enjoytrip.domain;

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
    public Board(Long boardId, String content, String title, User user) {
        this.boardId = boardId;
        this.content = content;
        this.title = title;
        this.user = user;
    }

    public void updateBoard(String title, String content, List<BoardImage> boardImages) {
        this.title = title;
        this.content = content;
        this.boardImages = new ArrayList<>();
        boardImages.forEach(this::addImage);
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 연관 관계 편의 메서드
     */
    public void addImage(BoardImage boardImage) {
        boardImages.add(boardImage);
        boardImage.addBoard(this);
    }
}
