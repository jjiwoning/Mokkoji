package com.ssafy.Mokkoji.core.attraction.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
	public Gugun(
		final int gugunCode,
		final String gugunName,
		final Sido sido
	) {
		this.gugunCode = gugunCode;
		this.gugunName = gugunName;
		this.sido = sido;
	}
}
