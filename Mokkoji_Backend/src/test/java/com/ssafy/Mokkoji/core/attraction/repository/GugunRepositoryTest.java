package com.ssafy.Mokkoji.core.attraction.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ssafy.Mokkoji.core.attraction.domain.Gugun;
import com.ssafy.Mokkoji.core.attraction.domain.Sido;

@DisplayName("GugunRepository 단위 테스트")
@DataJpaTest
class GugunRepositoryTest {

	@Autowired
	private GugunRepository gugunRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	@DisplayName("시, 도에 속하는 모든 구,군을 반환한다.")
	void findBySidoCode() {
		// given
		Sido sido = getSido(1, "테스트1");

		Gugun gugun1 = getGugun(sido, 1, "테스트1");
		Gugun gugun2 = getGugun(sido, 2, "테스트2");
		Gugun gugun3 = getGugun(sido, 3, "테스트3");
		Gugun gugun4 = getGugun(sido, 4, "테스트4");

		// when
		List<Gugun> result = gugunRepository.getAllGugunBySidoCode(sido.getSidoCode());

		// then
		assertThat(result).hasSize(4);
		assertThat(result).containsExactly(gugun1, gugun2, gugun3, gugun4);
	}

	private Sido getSido(int sidoCode, String name) {
		Sido sido = Sido.builder()
			.sidoCode(sidoCode)
			.sidoName(name)
			.build();

		entityManager.persist(sido);

		return sido;
	}

	private Gugun getGugun(Sido sido, int gugunCode, String title) {
		Gugun gugun = Gugun.builder()
			.sido(sido)
			.gugunCode(gugunCode)
			.gugunName(title)
			.build();

		entityManager.persist(gugun);

		return gugun;
	}
}
