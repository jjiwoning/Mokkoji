package integration.board;

import com.ssafy.Mokkoji.core.user.dto.response.TokenResponse;
import integration.IntegrationTest;
import integration.helper.BoardHelper;
import integration.helper.RestAssuredUtils;
import integration.helper.UserHelper;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Board 인수 테스트")
class BoardIntegrationTest extends IntegrationTest {

    @Test
    @DisplayName("게시글을 등록할 수 있다.")
    void write() {
        // given
        TokenResponse tokenResponse = UserHelper.signupAndLogin();

        Map<String, Object> formData = new HashMap<>();
        formData.put("title", "hello");
        formData.put("content", "hello");

        // when
        ExtractableResponse<Response> response = BoardHelper.addBoard(formData, tokenResponse.getAccessToken(), tokenResponse.getRefreshToken());

        // then
        assertThat(response.statusCode()).isEqualTo(200);
    }
}
