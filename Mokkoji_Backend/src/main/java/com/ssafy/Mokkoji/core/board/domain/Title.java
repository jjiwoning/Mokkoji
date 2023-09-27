package com.ssafy.Mokkoji.core.board.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class Title {

    private String title;

    private Title(final String title) {
        validate(title);
        this.title = title;
    }

    public static Title from(final String title) {
        return new Title(title);
    }

    private void validate(final String title) {
        if (!StringUtils.hasText(title)) {
            // TODO: 2023/09/27 추후 상황에 맞는 예외 만들어서 추가하기
            throw new IllegalArgumentException();
        }
    }
}
