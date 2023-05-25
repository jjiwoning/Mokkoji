package com.ssafy.enjoytrip.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttractionAddRequestDto {
    private int contentId;
    private int contentTypeId;
    private String title;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String firstImage;
    private String firstImage2;
    private String sidoName;
    private String gugunName;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
