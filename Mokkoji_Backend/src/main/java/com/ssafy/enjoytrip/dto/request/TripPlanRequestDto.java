package com.ssafy.enjoytrip.dto.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
