package com.ssafy.enjoytrip.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserInviteDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long teamId;
}
