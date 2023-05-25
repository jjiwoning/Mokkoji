package com.ssafy.enjoytrip.dto.response;

import com.ssafy.enjoytrip.domain.AttractionInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Attraction List Response 할 때 사용할 응답 객체
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttractionListResponseDto {

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

    public AttractionListResponseDto(AttractionInfo attractionInfo) {
        this.contentId = attractionInfo.getContentId();
        this.contentTypeId = attractionInfo.getContentTypeId();
        this.title = attractionInfo.getTitle();
        this.addr1 = attractionInfo.getAddr1();
        this.addr2 = attractionInfo.getAddr2();
        this.zipcode = attractionInfo.getZipcode();
        this.firstImage = attractionInfo.getFirstImage();
        this.firstImage2 = attractionInfo.getFirstImage2();
        this.sidoName = attractionInfo.getSido().getSidoName();
        this.gugunName = attractionInfo.getGugun().getGugunName();
        this.latitude = attractionInfo.getLatitude();
        this.longitude = attractionInfo.getLongitude();
    }
}
