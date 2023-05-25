package com.ssafy.enjoytrip.domain;


import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
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
	
}
