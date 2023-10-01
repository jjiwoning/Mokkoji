package integration.user;

import com.ssafy.Mokkoji.core.user.dto.request.UserJoinRequest;
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
}
