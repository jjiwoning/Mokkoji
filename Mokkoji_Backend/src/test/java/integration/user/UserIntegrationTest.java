package integration.user;

import com.ssafy.Mokkoji.core.user.dto.request.UserJoinRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserLoginRequest;
import com.ssafy.Mokkoji.core.user.dto.response.TokenResponse;
import integration.IntegrationTest;
import integration.helper.RestAssuredUtils;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("User 인수 테스트")
class UserIntegrationTest extends IntegrationTest {

    @Test
    @DisplayName("회원 정보를 입력하면 회원 가입이 완료된다.")
    void join() {
        // given
        UserJoinRequest request = new UserJoinRequest(
                "hello123",
                "hello123",
                "hello123@mail.com",
                "최탐탐", "tamtam1@",
                "01012341234");

        // when
        ExtractableResponse<Response> result = RestAssuredUtils.post("/user/signup", request);

        // then
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("아이디 비밀번호를 입력하면 로그인에 성공한다.")
    void login() {
        // given
        UserJoinRequest signUpRequest = new UserJoinRequest(
                "hello123",
                "hello123",
                "hello123@mail.com",
                "최탐탐",
                "tamtam1@",
                "01012341234");

        RestAssuredUtils.post("/user/signup", signUpRequest);

        UserLoginRequest userLoginRequest = new UserLoginRequest("hello123", "tamtam1@");

        // when
        ExtractableResponse<Response> result = RestAssuredUtils.post("/user/login", userLoginRequest);

        // then
        TokenResponse response = result.as(TokenResponse.class);
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getAccessToken()).isNotBlank();
        assertThat(response.getRefreshToken()).isNotBlank();
        assertThat(response.getMessage()).isEqualTo("Create Token");
    }
}
