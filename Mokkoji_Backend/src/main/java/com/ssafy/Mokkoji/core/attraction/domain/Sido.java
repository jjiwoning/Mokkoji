package com.ssafy.Mokkoji.core.attraction.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "sidos")
@Entity
@Getter
@NoArgsConstructor
public class Sido {

	@Id
	private int sidoCode;
	private String sidoName;
	
	public Sido(
			final int sidoCode,
			final String sidoName
	) {
		this.sidoCode = sidoCode;
		this.sidoName = sidoName;
	}
	
}
