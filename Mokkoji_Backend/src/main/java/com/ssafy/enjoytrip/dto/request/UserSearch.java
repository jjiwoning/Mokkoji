package com.ssafy.enjoytrip.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSearch {

    @NotBlank(message = "검색어 입력은 필수입니다.")
    private String nickname;

    @Builder.Default
    private Integer page = 1;

    @Builder.Default
    private Integer size = 20;

    public long getOffset() {
        return (long) (Math.max(1, page) - 1) * Math.min(size, 2000);
    }

}
