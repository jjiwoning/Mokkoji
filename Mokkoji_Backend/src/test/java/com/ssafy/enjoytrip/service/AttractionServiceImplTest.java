package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.domain.AttractionInfo;
import com.ssafy.enjoytrip.dto.request.AttractionSearch;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AttractionServiceImplTest {

    @Autowired
    private AttractionService attractionService;

    @Test
    @DisplayName("여행지 조회 테스트1, 검색 조건")
    void test1() {
        AttractionSearch attractionSearch = AttractionSearch.builder().title("산").build();
        List<AttractionInfo> allAttraction = attractionService.getAllAttraction(attractionSearch);
        for (AttractionInfo attractionInfo : allAttraction) {
            Assertions.assertThat(attractionInfo.getTitle()).contains("산");
        }
    }

    @Test
    @DisplayName("여행지 조회 테스트2, 사이즈 조절")
    void test2() {
        AttractionSearch attractionSearch = AttractionSearch.builder().size(10).build();
        List<AttractionInfo> allAttraction = attractionService.getAllAttraction(attractionSearch);
        Assertions.assertThat(allAttraction.size()).isEqualTo(10);
    }
}