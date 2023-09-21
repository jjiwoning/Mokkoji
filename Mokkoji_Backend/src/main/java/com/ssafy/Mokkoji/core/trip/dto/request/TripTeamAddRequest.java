package com.ssafy.Mokkoji.core.trip.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TripTeamAddRequest {

    @NotBlank
    private String teamName;

}
