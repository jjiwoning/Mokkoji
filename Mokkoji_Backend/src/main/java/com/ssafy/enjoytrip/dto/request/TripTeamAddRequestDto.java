package com.ssafy.enjoytrip.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TripTeamAddRequestDto {

    @NotBlank
    private String teamName;

}
