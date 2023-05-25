package com.ssafy.enjoytrip.domain;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sidoCode;
	private String sidoName;
	
	public Sido(int sidoCode, String sidoName) {
		this.sidoCode = sidoCode;
		this.sidoName = sidoName;
	}
	
}
