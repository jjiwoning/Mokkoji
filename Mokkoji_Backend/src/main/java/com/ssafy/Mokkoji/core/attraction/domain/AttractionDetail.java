package com.ssafy.Mokkoji.core.attraction.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "attraction_details")
@Entity
@Getter
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

	@Builder
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
