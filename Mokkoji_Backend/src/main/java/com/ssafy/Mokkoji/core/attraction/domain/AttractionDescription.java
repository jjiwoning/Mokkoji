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

@Table(name = "attraction_descriptions")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttractionDescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contentId;
	private String homepage;
	private String overview;
	private String telName;

	@Builder
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
