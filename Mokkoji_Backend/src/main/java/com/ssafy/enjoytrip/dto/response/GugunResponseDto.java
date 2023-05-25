package com.ssafy.enjoytrip.dto.response;

import com.ssafy.enjoytrip.domain.Gugun;
import lombok.Data;

@Data
public class GugunResponseDto {
    private int gugunCode;
    private String gugunName;

    public GugunResponseDto(Gugun gugun) {
        this.gugunCode = gugun.getGugunCode();
        this.gugunName = gugun.getGugunName();
    }
}
