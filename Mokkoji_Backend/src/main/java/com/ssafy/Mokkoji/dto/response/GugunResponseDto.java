package com.ssafy.Mokkoji.dto.response;

import com.ssafy.Mokkoji.domain.Gugun;
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
