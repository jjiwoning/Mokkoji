package com.ssafy.Mokkoji.core.trip.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamBoardAddRequest {

    private String content;

    private String title;

    public TeamBoardAddRequest(String content, String title) {
        this.content = content;
        this.title = title;
    }
}
