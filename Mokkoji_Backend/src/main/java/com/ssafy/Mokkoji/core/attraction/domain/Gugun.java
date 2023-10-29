package com.ssafy.Mokkoji.core.attraction.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "guguns")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gugun {

	@Id
	private int gugunCode;

	private String gugunName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sido_code")
	private Sido sido;

	@Builder
	public Gugun(int gugunCode, String gugunName, Sido sido) {
		this.gugunCode = gugunCode;
		this.gugunName = gugunName;
		this.sido = sido;
	}
}
