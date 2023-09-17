package com.ssafy.Mokkoji.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
