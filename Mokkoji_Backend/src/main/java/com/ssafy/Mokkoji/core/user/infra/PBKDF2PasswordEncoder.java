package com.ssafy.Mokkoji.core.user.infra;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Component;

import com.ssafy.Mokkoji.core.user.domain.PasswordEncoder;
import com.ssafy.Mokkoji.core.user.exception.PasswordEncodingException;

@Component
public class PBKDF2PasswordEncoder implements PasswordEncoder {

	private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
	private static final int ITERATION_COUNT = 65536;
	private static final int KEY_LENGTH = 256;
	private static final int SALT = 16;

	@Override
	public String encode(final String password) {
		return encode(password, getSalt());
	}

	@Override
	public boolean matches(final String rawPassword, final String encodedPassword) {
		byte[] salt = getSalt(encodedPassword);
		String hashedPassword = encode(rawPassword, salt);

		return encodedPassword.equals(hashedPassword);
	}

	private String encode(final String password, final byte[] salt) {
		try {
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);
			SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);

			byte[] hash = factory.generateSecret(spec).getEncoded();

			String base64Hash = Base64.getEncoder().encodeToString(hash);
			String base64Salt = Base64.getEncoder().encodeToString(salt);
			return base64Salt + base64Hash;
		} catch (Exception e) {
			throw new PasswordEncodingException(e);
		}
	}

	private byte[] getSalt() {
		Random random = new SecureRandom();
		byte[] salt = new byte[SALT];
		random.nextBytes(salt);
		return salt;
	}

	private byte[] getSalt(final String digest) {
		return Base64.getDecoder().decode(digest.substring(0, 24));
	}
}
