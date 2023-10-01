package com.ssafy.Mokkoji.core.attraction.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "attraction_details")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
	
	public AttractionDetail(
			final int contentId,
			final String cat1,
			final String cat2,
			final String cat3,
			final String createdTime,
			final String modifiedTime,
			final String bookTour
	) {
		this.contentId = contentId;
		this.cat1 = cat1;
		this.cat2 = cat2;
		this.cat3 = cat3;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
		this.bookTour = bookTour;
	}

}
