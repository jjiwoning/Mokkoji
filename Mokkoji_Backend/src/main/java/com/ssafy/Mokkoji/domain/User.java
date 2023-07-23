package com.ssafy.Mokkoji.domain;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

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
    public User(Long userId, String loginId, String nickname, String mail, String name, String password, String phoneNumber, String refreshToken) {
        this.userId = userId;
        this.loginId = loginId;
        this.nickname = nickname;
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.refreshToken = refreshToken;
    }

    public boolean loginLogic(String password) {
        return this.password.equals(password);
    }

    public void addRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updateUser(User user) {
        this.mail = user.getMail();
        this.nickname = user.getNickname();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
    }
}
