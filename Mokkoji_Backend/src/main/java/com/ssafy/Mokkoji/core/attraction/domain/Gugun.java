package com.ssafy.Mokkoji.core.attraction.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Gugun {

	@Id
	private int gugunCode;

	private String gugunName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sido_code")
	private Sido sido;

}
