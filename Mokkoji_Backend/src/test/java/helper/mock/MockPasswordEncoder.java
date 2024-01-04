package helper.mock;

import com.ssafy.Mokkoji.core.user.domain.PasswordEncoder;

public class MockPasswordEncoder implements PasswordEncoder {

	public static final String ENCODED_PASSWORD = "abcsdweqwersad";

	@Override
	public String encode(String password) {
		return ENCODED_PASSWORD;
	}

	@Override
	public boolean matches(String rawPassword, String encodedPassword) {
		return encodedPassword.equals(ENCODED_PASSWORD);
	}
}
