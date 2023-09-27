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
public class Content {

    private String content;

    private Content(final String content) {
        validate(content);
        this.content = content;
    }

    public static Content from(final String content) {
        return new Content(content);
    }

    private void validate(final String content) {
        if (!StringUtils.hasText(content)) {
            // TODO: 2023/09/27 추후 상황에 맞는 예외 만들어서 추가하기
            throw new IllegalArgumentException();
        }
    }
}
