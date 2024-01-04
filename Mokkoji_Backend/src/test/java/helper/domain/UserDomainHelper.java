package helper.domain;

import com.ssafy.Mokkoji.core.user.domain.User;

public class UserDomainHelper {

	public static User.UserBuilder buildDefaultUser() {
		return User.builder()
			.nickname("hello123")
			.loginId("hello123")
			.encodedPassword("encodedPassword;p")
			.name("김김김")
			.mail("hello123@hello.com")
			.phoneNumber("01000000000");
	}
}
