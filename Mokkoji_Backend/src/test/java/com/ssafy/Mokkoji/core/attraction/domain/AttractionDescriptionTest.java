package com.ssafy.Mokkoji.core.attraction.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("AttractionDescription 단위 테스트")
class AttractionDescriptionTest {

    @Test
    @DisplayName("AttractionDescription 생성 테스트")
    void create() {
        // given
        String homepage = "testHomepage";
        String overview = "testOverView";
        String telName = "testTelName";

        // when
        AttractionDescription attractionDescription = AttractionDescription.builder()
                .homepage(homepage)
                .overview(overview)
                .telName(telName)
                .build();

        // then
        Assertions.assertAll(
                () -> assertThat(attractionDescription)
                        .extracting(AttractionDescription::getHomepage).isEqualTo(homepage),
                () -> assertThat(attractionDescription)
                        .extracting(AttractionDescription::getOverview).isEqualTo(overview),
                () -> assertThat(attractionDescription)
                        .extracting(AttractionDescription::getTelName).isEqualTo(telName)
        );
    }
}
