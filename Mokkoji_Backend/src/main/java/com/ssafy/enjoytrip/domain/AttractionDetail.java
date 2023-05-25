package com.ssafy.enjoytrip.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AttractionDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contentId;
	private String cat1;
	private String cat2;
	private String cat3;
	private String createdTime;
	private String modifiedTime;
	private String bookTour;
	
	public AttractionDetail(int contentId, String cat1, String cat2, String cat3, String createdTime,
			String modifiedTime, String bookTour) {
		this.contentId = contentId;
		this.cat1 = cat1;
		this.cat2 = cat2;
		this.cat3 = cat3;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
		this.bookTour = bookTour;
	}
	
}
