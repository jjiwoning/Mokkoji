package com.ssafy.enjoytrip.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardImageId;

    private String userFileName; // 유저가 저장하는 이름

    private String storedFileName; // db에 저장할 파일 이름

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public void addBoard(Board board) {
        this.board = board;
    }
}
