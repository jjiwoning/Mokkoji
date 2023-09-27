package com.ssafy.Mokkoji.core.model;

import com.ssafy.Mokkoji.core.model.exception.ContentEmptyException;
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
            throw new ContentEmptyException();
        }
    }
}
