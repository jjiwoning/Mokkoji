package com.ssafy.Mokkoji.core.helper;

import com.ssafy.Mokkoji.core.attraction.domain.AttractionInfo;
import com.ssafy.Mokkoji.core.attraction.domain.Gugun;
import com.ssafy.Mokkoji.core.attraction.domain.Sido;

public class AttractionDomainHelper {

	private AttractionDomainHelper() {
	}

	public static Sido getSido(int sidoCode, String name) {
		return Sido.builder()
			.sidoCode(sidoCode)
			.sidoName(name)
			.build();
	}

	public static Gugun getGugun(Sido sido, int gugunCode, String title) {
		return Gugun.builder()
			.sido(sido)
			.gugunCode(gugunCode)
			.gugunName(title)
			.build();
	}

	public static AttractionInfo getAttraction(Gugun targetGugun, String title) {
		return AttractionInfo.builder()
			.gugun(targetGugun)
			.title(title)
			.build();
	}
}
