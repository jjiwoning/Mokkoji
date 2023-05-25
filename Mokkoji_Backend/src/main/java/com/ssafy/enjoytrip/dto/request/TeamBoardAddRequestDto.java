package com.ssafy.enjoytrip.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamBoardAddRequestDto {

    private String content;

    private String title;

    public TeamBoardAddRequestDto(String content, String title) {
        this.content = content;
        this.title = title;
    }
}
