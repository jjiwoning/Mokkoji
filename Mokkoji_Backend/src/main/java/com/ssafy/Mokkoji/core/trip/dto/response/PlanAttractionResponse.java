package com.ssafy.Mokkoji.core.trip.dto.response;

import com.ssafy.Mokkoji.core.trip.domain.PlanAttraction;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlanAttractionResponse {

    private Long planAttractionId;

    private int contentTypeId;

    private String title;

    private String addr1;

    private String zipcode;

    private String tel;

    private BigDecimal latitude;

    private BigDecimal longitude;

    public PlanAttractionResponse(PlanAttraction planAttraction) {
        this.planAttractionId = planAttraction.getPlanAttractionId();
        this.contentTypeId = planAttraction.getAttractionInfo().getContentTypeId();
        this.title = planAttraction.getAttractionInfo().getTitle();
        this.addr1 = planAttraction.getAttractionInfo().getAddr1();
        this.zipcode = planAttraction.getAttractionInfo().getZipcode();
        this.tel = planAttraction.getAttractionInfo().getTel();
        this.latitude = planAttraction.getAttractionInfo().getLatitude();
        this.longitude = planAttraction.getAttractionInfo().getLongitude();
    }
}
