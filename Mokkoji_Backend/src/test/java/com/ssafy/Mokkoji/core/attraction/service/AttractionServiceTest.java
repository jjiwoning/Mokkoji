package com.ssafy.Mokkoji.core.attraction.service;

import static com.ssafy.Mokkoji.core.helper.AttractionDomainHelper.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ssafy.Mokkoji.core.attraction.domain.Gugun;
import com.ssafy.Mokkoji.core.attraction.domain.Sido;
import com.ssafy.Mokkoji.core.attraction.dto.request.AttractionSearch;
import com.ssafy.Mokkoji.core.attraction.dto.response.AttractionResponse;
import com.ssafy.Mokkoji.core.attraction.dto.response.GugunResponse;
import com.ssafy.Mokkoji.core.attraction.repository.AttractionInfoRepository;
import com.ssafy.Mokkoji.core.attraction.repository.GugunRepository;

@DisplayName("AttractionService 단위 테스트")
@ExtendWith(MockitoExtension.class)
class AttractionServiceTest {

	@Mock
	private AttractionInfoRepository attractionInfoRepository;

	@Mock
	private GugunRepository gugunRepository;

	@InjectMocks
	private AttractionServiceImpl attractionService;

	@Test
	@DisplayName("검색한 여행지 정보를 반환한다.")
	void search() {
		// given
		AttractionSearch search = AttractionSearch.builder()
			.gugunCode(1)
			.sidoCode(1)
			.title("공원")
			.build();

		Sido sido = getSido(1, "테스트");
		Gugun gugun = getGugun(sido, 2, "테스트");

		when(attractionInfoRepository.getAllAttractionList(search))
			.thenReturn(
				Arrays.asList(getAttraction(gugun, "공원1"),
					getAttraction(gugun, "공원2"),
					getAttraction(gugun, "공원3"))
			);

		// when
		List<AttractionResponse> result = attractionService.getAllAttraction(search);

		// then
		assertThat(result).extracting(AttractionResponse::getTitle)
			.containsExactly("공원1", "공원2", "공원3");
	}

	@Test
	@DisplayName("시, 도를 바탕으로 모든 구, 군을 찾는다.")
	void serachGugun() {
		// given
		Sido sido = getSido(1, "테스트");

		Gugun gugun1 = getGugun(sido, 2, "테스트2");
		Gugun gugun2 = getGugun(sido, 3, "테스트3");
		Gugun gugun3 = getGugun(sido, 4, "테스트4");
		Gugun gugun4 = getGugun(sido, 5, "테스트5");

		when(gugunRepository.getAllGugunBySidoCode(sido.getSidoCode()))
			.thenReturn(Arrays.asList(gugun1, gugun2, gugun3, gugun4));

		// when
		List<GugunResponse> result = attractionService.getAllGugunBySidoCode(sido.getSidoCode());

		// then
		assertThat(result).extracting(GugunResponse::getGugunName)
			.containsExactly(
				gugun1.getGugunName(), gugun2.getGugunName(), gugun3.getGugunName(), gugun4.getGugunName());
	}
}
