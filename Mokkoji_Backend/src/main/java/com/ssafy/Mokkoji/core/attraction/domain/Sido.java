package com.ssafy.Mokkoji.core.attraction.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "sidos")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sido {

	@Id
	private int sidoCode;
	private String sidoName;

	@Builder
	public Sido(
			final int sidoCode,
			final String sidoName
	) {
		this.sidoCode = sidoCode;
		this.sidoName = sidoName;
	}
	
}
