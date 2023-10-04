package integration.helper;

import com.ssafy.Mokkoji.core.user.dto.request.UserJoinRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserLoginRequest;
import com.ssafy.Mokkoji.core.user.dto.response.TokenResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class UserHelper {

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

    public static TokenResponse login(final UserLoginRequest request) {
        return RestAssuredUtils.post("/user/login", request)
                .as(TokenResponse.class);
    }

    public static UserLoginRequest createUserLoginRequest() {
        return new UserLoginRequest("hello123", "tamtam1@");
    }

    public static TokenResponse signupAndLogin() {
        signup(createUserJoinRequest());
        return login(createUserLoginRequest());
    }
}
