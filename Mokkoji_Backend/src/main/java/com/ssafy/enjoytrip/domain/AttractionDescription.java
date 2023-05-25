package com.ssafy.enjoytrip.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AttractionDescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contentId;
	private String hompage;
	private String overview;
	private String telName;
	
	public AttractionDescription(int contentId, String hompage, String overview, String telName) {
		this.contentId = contentId;
		this.hompage = hompage;
		this.overview = overview;
		this.telName = telName;
	}
	
}
