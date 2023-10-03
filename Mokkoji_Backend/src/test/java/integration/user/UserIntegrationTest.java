package integration.user;

import com.ssafy.Mokkoji.core.user.dto.request.UserJoinRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserLoginRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserUpdateRequest;
import com.ssafy.Mokkoji.core.user.dto.response.TokenResponse;
import com.ssafy.Mokkoji.core.user.dto.response.UserIdResponse;
import integration.IntegrationTest;
import integration.helper.RestAssuredUtils;
import integration.helper.UserHelper;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("User 인수 테스트")
class UserIntegrationTest extends IntegrationTest {

    @Test
    @DisplayName("회원 정보를 입력하면 회원 가입이 완료된다.")
    void join() {
        // given
        UserJoinRequest request = UserHelper.createUserJoinRequest();

        // when
        ExtractableResponse<Response> result = UserHelper.signup(request);

        // then
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("아이디 비밀번호를 입력하면 로그인에 성공한다.")
    void login() {
        // given
        UserHelper.signup(UserHelper.createUserJoinRequest());

        UserLoginRequest userLoginRequest = UserHelper.createUserLoginRequest();

        // when
        ExtractableResponse<Response> result = RestAssuredUtils.post("/user/login", userLoginRequest);

        // then
        TokenResponse response = result.as(TokenResponse.class);
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getAccessToken()).isNotBlank();
        assertThat(response.getRefreshToken()).isNotBlank();
        assertThat(response.getMessage()).isEqualTo("Create Token");
    }

    @Test
    @DisplayName("이미 존재하는 아이디인 경우 false를 반환한다.")
    void checkDuplicateFalse() {
        // given
        UserJoinRequest signUpRequest = UserHelper.createUserJoinRequest();

        UserHelper.signup(signUpRequest);

        // when
        Map<String, String> params = new HashMap<>();
        params.put("loginId", signUpRequest.getLoginId());
        ExtractableResponse<Response> result = RestAssuredUtils.get("/user/signup/duplicate", params);

        // then
        Boolean response = result.as(Boolean.class);
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response).isFalse();
    }

    @Test
    @DisplayName("존재하지 않은 아이디인 경우 ture를 반환한다.")
    void checkDuplicateTrue() {
        // given
        UserJoinRequest signUpRequest = UserHelper.createUserJoinRequest();

        UserHelper.signup(signUpRequest);

        // when
        Map<String, String> params = new HashMap<>();
        params.put("loginId", signUpRequest.getLoginId() + "1234");
        ExtractableResponse<Response> result = RestAssuredUtils.get("/user/signup/duplicate", params);

        // then
        Boolean response = result.as(Boolean.class);
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response).isTrue();
    }

    @Test
    @DisplayName("회원 탈퇴를 할 수 있다.")
    void delete() {
        // given
        UserHelper.signup(UserHelper.createUserJoinRequest());
        TokenResponse token = UserHelper.login(UserHelper.createUserLoginRequest());

        // when
        ExtractableResponse<Response> result = RestAssuredUtils.delete("/user/delete", token.getAccessToken(), token.getRefreshToken());

        // then
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("로그아웃을 할 수 있다.")
    void logout() {
        // given
        UserHelper.signup(UserHelper.createUserJoinRequest());
        TokenResponse token = UserHelper.login(UserHelper.createUserLoginRequest());

        // when
        ExtractableResponse<Response> result = RestAssuredUtils.post("/user/logout", token.getAccessToken(), token.getRefreshToken());

        // then
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("회원 정보를 수정할 수 있다.")
    void update() {
        // given
        UserIdResponse userIdResponse = UserHelper.signup(UserHelper.createUserJoinRequest()).as(UserIdResponse.class);
        TokenResponse token = UserHelper.login(UserHelper.createUserLoginRequest());

        // when
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest("qqq@qqq.com", "qqqq", "qqq1234##", "01012341234");
        ExtractableResponse<Response> result = RestAssuredUtils.patch("/user/" + userIdResponse.getUserId() ,
                userUpdateRequest, token.getAccessToken(), token.getRefreshToken());

        // then
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
