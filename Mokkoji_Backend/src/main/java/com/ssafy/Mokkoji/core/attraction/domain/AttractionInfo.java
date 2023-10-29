package com.ssafy.Mokkoji.core.attraction.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "attraction_infos")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttractionInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contentId;
	private int contentTypeId;
	private String title;
	private String addr1;
	private String addr2;
	private String zipcode;
	private String tel;
	private String firstImage;
	private String firstImage2;
	private int readcount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sido_code")
	private Sido sido;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gugun_code")
	private Gugun gugun;

	private BigDecimal latitude;
	private BigDecimal longitude;
	private String mlevel;

	@Builder
	public AttractionInfo(
			final int contentId,
			final int contentTypeId,
			final String title,
			final String addr1,
			final String addr2,
			final String zipcode,
			final String tel,
			final String firstImage,
			final String firstImage2,
			final int readcount,
			final Gugun gugun,
			final BigDecimal latitude,
			final BigDecimal longitude,
			final String mlevel
	) {
		this.contentId = contentId;
		this.contentTypeId = contentTypeId;
		this.title = title;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zipcode = zipcode;
		this.tel = tel;
		this.firstImage = firstImage;
		this.firstImage2 = firstImage2;
		this.readcount = readcount;
		this.sido = gugun.getSido();
		this.gugun = gugun;
		this.latitude = latitude;
		this.longitude = longitude;
		this.mlevel = mlevel;
	}
}
