package com.ssafy.Mokkoji.core.model;

import com.ssafy.Mokkoji.core.model.exception.TitleEmptyException;
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
            throw new TitleEmptyException();
        }
    }
}
