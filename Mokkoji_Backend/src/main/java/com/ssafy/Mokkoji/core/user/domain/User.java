package com.ssafy.Mokkoji.core.user.domain;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ssafy.Mokkoji.core.model.BaseTimeEntity;
import com.ssafy.Mokkoji.core.user.domain.vo.LoginId;
import com.ssafy.Mokkoji.core.user.domain.vo.Mail;
import com.ssafy.Mokkoji.core.user.domain.vo.NickName;
import com.ssafy.Mokkoji.core.user.domain.vo.Password;
import com.ssafy.Mokkoji.core.user.exception.LoginFailException;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Embedded
	private LoginId loginId;

	@Embedded
	private NickName nickname;

	@Embedded
	private Mail mail;

	private String name;

	@Embedded
	private Password password;

	private String phoneNumber;

	private String refreshToken;

	@Builder
	public User(
		final Long userId,
		final String loginId,
		final String nickname,
		final String mail,
		final String name,
		final String encodedPassword,
		final String phoneNumber,
		final String refreshToken
	) {
		this.userId = userId;
		this.loginId = LoginId.from(loginId);
		this.nickname = NickName.from(nickname);
		this.mail = Mail.from(mail);
		this.name = name;
		this.password = Password.from(encodedPassword);
		this.phoneNumber = phoneNumber;
		this.refreshToken = refreshToken;
	}

	public void login(final String rawPassword, final PasswordEncoder passwordEncoder) {
		if (!this.password.matches(rawPassword, passwordEncoder)) {
			throw new LoginFailException();
		}
	}

	public void addRefreshToken(final String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void updateUser(
		final String mail,
		final String nickname,
		final String encodedPassword,
		final String phoneNumber
	) {
		this.mail = Mail.from(mail);
		this.nickname = NickName.from(nickname);
		this.password = Password.from(encodedPassword);
		this.phoneNumber = phoneNumber;
	}

	public boolean isSameUser(final Long userId) {
		return Objects.equals(this.userId, userId);
	}
}
