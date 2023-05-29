package com.ssafy.Mokkoji.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class TripPlanRequestDto {

    @NotNull
    private String planName;

    @NotNull
    private String planContent;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
