package com.ssafy.Mokkoji.core.user.domain;

import javax.persistence.*;

import com.ssafy.Mokkoji.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String loginId;

    private String nickname;

    private String mail;

    private String name;

    private String password;

    private String phoneNumber;

    private String refreshToken;

    @Builder
    public User(
            final Long userId,
            final String loginId,
            final String nickname,
            final String mail,
            final String name,
            final String password,
            final String phoneNumber,
            final String refreshToken
    ) {
        this.userId = userId;
        this.loginId = loginId;
        this.nickname = nickname;
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.refreshToken = refreshToken;
    }

    public boolean login(final String password) {
        return this.password.equals(password);
    }

    public void addRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updateUser(final User user) {
        this.mail = user.mail;
        this.nickname = user.nickname;
        this.password = user.password;
        this.phoneNumber = user.phoneNumber;
    }

    public boolean isSameUser(Long userId) {
        return Objects.equals(this.userId, userId);
    }
}
