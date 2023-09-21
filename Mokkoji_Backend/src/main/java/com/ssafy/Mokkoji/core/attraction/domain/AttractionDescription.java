package com.ssafy.Mokkoji.core.attraction.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttractionDescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contentId;
	private String homepage;
	private String overview;
	private String telName;
	
	public AttractionDescription(
			final int contentId,
			final String homepage,
			final String overview,
			final String telName
	) {
		this.contentId = contentId;
		this.homepage = homepage;
		this.overview = overview;
		this.telName = telName;
	}
	
}
