package com.ssafy.Mokkoji.core.attraction.dto.response;

import com.ssafy.Mokkoji.core.attraction.domain.Gugun;

import lombok.Data;

@Data
public class GugunResponse {
	private int gugunCode;
	private String gugunName;

	public GugunResponse(Gugun gugun) {
		this.gugunCode = gugun.getGugunCode();
		this.gugunName = gugun.getGugunName();
	}
}
