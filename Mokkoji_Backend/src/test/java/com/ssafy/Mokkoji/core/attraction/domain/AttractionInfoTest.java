package com.ssafy.Mokkoji.core.attraction.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("AttractionInfo 단위 테스트")
class AttractionInfoTest {

    @Test
    @DisplayName("AttractionInfo 생성 테스트")
    void create() {
        // given
        String title = "testTitle";
        String addr1 = "testAddr1";
        String addr2 = "testAddr2";
        String zipcode = "testZipCode";
        String tel = "testTel";
        String firstImage = "testFirstImage";
        String firstImage2 = "testFirstImage2";
        int readcount = 0;
        Sido sido = Sido.builder().sidoCode(1).sidoName("testSi").build();
        Gugun gugun = Gugun.builder().gugunCode(1).gugunName("testGugun").sido(sido).build();

        // when
        AttractionInfo attractionInfo = AttractionInfo.builder()
                .title(title)
                .addr1(addr1)
                .addr2(addr2)
                .zipcode(zipcode)
                .tel(tel)
                .firstImage(firstImage)
                .firstImage2(firstImage2)
                .readcount(readcount)
                .gugun(gugun)
                .build();

        // then
        Assertions.assertAll(
                () -> assertThat(attractionInfo)
                        .extracting(AttractionInfo::getTitle).isEqualTo(title),
                () -> assertThat(attractionInfo)
                        .extracting(AttractionInfo::getAddr1).isEqualTo(addr1),
                () -> assertThat(attractionInfo)
                        .extracting(AttractionInfo::getAddr2).isEqualTo(addr2),
                () -> assertThat(attractionInfo)
                        .extracting(AttractionInfo::getZipcode).isEqualTo(zipcode),
                () -> assertThat(attractionInfo)
                        .extracting(AttractionInfo::getTel).isEqualTo(tel),
                () -> assertThat(attractionInfo)
                        .extracting(AttractionInfo::getFirstImage).isEqualTo(firstImage),
                () -> assertThat(attractionInfo)
                        .extracting(AttractionInfo::getFirstImage2).isEqualTo(firstImage2),
                () -> assertThat(attractionInfo)
                        .extracting(AttractionInfo::getReadcount).isEqualTo(readcount),
                () -> assertThat(attractionInfo)
                        .extracting(AttractionInfo::getSido).isEqualTo(sido),
                () -> assertThat(attractionInfo)
                        .extracting(AttractionInfo::getGugun).isEqualTo(gugun)
        );
    }
}
