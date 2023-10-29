package com.ssafy.Mokkoji.core.attraction.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("AttractionDetail 단위 테스트")
class AttractionDetailTest {

    @Test
    @DisplayName("AttractionDetail 생성 테스트")
    void create() {
        // given
        String cat1 = "testCat1";
        String cat2 = "testCat2";
        String cat3 = "testCat3";
        String createdTime = "2023-09-01";
        String modifiedTime = "2023-09-02";
        String bookTour = "testBookTour";

        // when
        AttractionDetail attractionDetail = AttractionDetail.builder()
                .cat1(cat1)
                .cat2(cat2)
                .cat3(cat3)
                .createdTime(createdTime)
                .modifiedTime(modifiedTime)
                .bookTour(bookTour)
                .build();

        // then
        Assertions.assertAll(
                () -> assertThat(attractionDetail)
                        .extracting(AttractionDetail::getCat1).isEqualTo(cat1),
                () -> assertThat(attractionDetail)
                        .extracting(AttractionDetail::getCat2).isEqualTo(cat2),
                () -> assertThat(attractionDetail)
                        .extracting(AttractionDetail::getCat3).isEqualTo(cat3),
                () -> assertThat(attractionDetail)
                        .extracting(AttractionDetail::getCreatedTime).isEqualTo(createdTime),
                () -> assertThat(attractionDetail)
                        .extracting(AttractionDetail::getModifiedTime).isEqualTo(modifiedTime),
                () -> assertThat(attractionDetail)
                        .extracting(AttractionDetail::getBookTour).isEqualTo(bookTour)
        );
    }
}
