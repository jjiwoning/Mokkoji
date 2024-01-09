package integration.helper;

import com.ssafy.Mokkoji.core.user.dto.request.AccessTokenRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserJoinRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserLoginRequest;
import com.ssafy.Mokkoji.core.user.dto.response.AccessTokenResponse;
import com.ssafy.Mokkoji.core.user.dto.response.RefreshTokenResponse;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class UserIntegrationHelper {

	public static ExtractableResponse<Response> signup(final UserJoinRequest request) {
		return RestAssuredUtils.post("/user/signup", request);
	}

	public static UserJoinRequest createUserJoinRequest() {
		return new UserJoinRequest(
			"hello123",
			"hello123",
			"hello123@mail.com",
			"최탐탐",
			"tamtam1@",
			"01012341234");
	}

	public static RefreshTokenResponse login(final UserLoginRequest request) {
		return RestAssuredUtils.post("/user/login", request)
			.as(RefreshTokenResponse.class);
	}

	public static UserLoginRequest createUserLoginRequest() {
		return new UserLoginRequest("hello123", "tamtam1@");
	}

	public static AccessTokenResponse signupAndLogin() {
		signup(createUserJoinRequest());
		RefreshTokenResponse response = login(createUserLoginRequest());
		return generateAccessToken(new AccessTokenRequest(response.getRefreshToken()));
	}

	public static AccessTokenResponse generateAccessToken(final AccessTokenRequest request) {
		return RestAssuredUtils.post("/user/access-token", request)
			.as(AccessTokenResponse.class);
	}
}
