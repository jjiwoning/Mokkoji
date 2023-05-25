package com.ssafy.enjoytrip.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionSearch {

    @NotBlank(message = "검색어 입력은 필수입니다.")
    String title;

    Integer contentTypeId;

    Integer sidoCode;

    Integer gugunCode;

    @Builder.Default
    private Integer page = 1;

    @Builder.Default
    private Integer size = 20;

    public long getOffset() {
        return (long) (Math.max(1, page) - 1) * Math.min(size, 2000);
    }
}
