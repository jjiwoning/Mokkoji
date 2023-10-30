package com.ssafy.Mokkoji.core.attraction.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionSearch {

	@NotBlank(message = "검색어 입력은 필수입니다.")
	private String title;

	private Integer contentTypeId;

	private Integer sidoCode;

	private Integer gugunCode;

	@Builder.Default
	private Integer page = 1;

	@Builder.Default
	private Integer size = 20;

	public long getOffset() {
		return (long)(Math.max(1, page) - 1) * Math.min(size, 2000);
	}
}
