package com.ssafy.Mokkoji.core.trip.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserInviteRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long teamId;
}
