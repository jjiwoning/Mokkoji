package com.ssafy.enjoytrip.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;
    private String message;
}
