package com.ssafy.Mokkoji.core.attraction.repository;

import static com.ssafy.Mokkoji.core.helper.AttractionDomainHelper.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ssafy.Mokkoji.core.attraction.domain.AttractionInfo;
import com.ssafy.Mokkoji.core.attraction.domain.Gugun;
import com.ssafy.Mokkoji.core.attraction.domain.Sido;
import com.ssafy.Mokkoji.core.attraction.dto.request.AttractionSearch;

@DisplayName("AttractionInfoRepository 단위 테스트")
@DataJpaTest
class AttractionInfoRepositoryTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private AttractionInfoRepository attractionInfoRepository;

	@Test
	@DisplayName("검색 조건에 맞는 Attraction을 검색한다.")
	void search() {
		// given
		Sido targetSido = getSido(1, "테스트");
		Sido otherSido = getSido(2, "테스트2");

		Gugun targetGugun = getGugun(targetSido, 1, "테스트");
		Gugun otherGugun = getGugun(otherSido, 2, "테스트2");

		AttractionInfo attraction1 = getAttraction(targetGugun, "테스트12");
		AttractionInfo attraction2 = getAttraction(targetGugun, "테스트3");
		AttractionInfo attraction3 = getAttraction(targetGugun, "테스트18");
		AttractionInfo attraction4 = getAttraction(otherGugun, "테스트18");

		entityManager.persist(targetSido);
		entityManager.persist(otherSido);

		entityManager.persist(targetGugun);
		entityManager.persist(otherGugun);

		entityManager.persist(attraction1);
		entityManager.persist(attraction2);
		entityManager.persist(attraction3);
		entityManager.persist(attraction4);

		AttractionSearch attractionSearch = AttractionSearch.builder()
			.gugunCode(targetGugun.getGugunCode())
			.sidoCode(targetSido.getSidoCode())
			.title("1")
			.build();

		// when
		List<AttractionInfo> result = attractionInfoRepository.getAllAttractionList(attractionSearch);

		// then
		assertThat(result).hasSize(2);
		assertThat(result).containsExactly(attraction1, attraction3);
	}
}
