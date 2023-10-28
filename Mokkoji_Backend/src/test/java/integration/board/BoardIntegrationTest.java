package integration.board;

import com.ssafy.Mokkoji.config.FileStoreTestConfig;
import com.ssafy.Mokkoji.core.user.dto.response.TokenResponse;
import integration.IntegrationTest;
import integration.helper.BoardHelper;
import integration.helper.RestAssuredUtils;
import integration.helper.UserHelper;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Board 인수 테스트")
@Import(FileStoreTestConfig.class)
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
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("이미지를 포함하여 게시글을 등록할 수 있다.")
    void writeWithImage() throws IOException {
        // given
        TokenResponse tokenResponse = UserHelper.signupAndLogin();

        Map<String, Object> formData = new HashMap<>();
        formData.put("title", "hello");
        formData.put("content", "hello");

        MockMultipartFile multipartFile = new MockMultipartFile("image.png", "test.png", MediaType.IMAGE_GIF_VALUE, "test".getBytes());

        // when
        ExtractableResponse<Response> response = BoardHelper.addBoard(formData, tokenResponse.getAccessToken(), tokenResponse.getRefreshToken(), multipartFile);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("게시글을 수정할 수 있다.")
    void getImage() throws IOException {
        // given
        TokenResponse tokenResponse = UserHelper.signupAndLogin();

        Map<String, Object> formData = new HashMap<>();
        formData.put("title", "hello");
        formData.put("content", "hello");

        MockMultipartFile multipartFile = new MockMultipartFile("image.png", "test.png", MediaType.IMAGE_GIF_VALUE, "test".getBytes());

        String location = BoardHelper.addBoard(formData, tokenResponse.getAccessToken(), tokenResponse.getRefreshToken(), multipartFile).header("location");
        Long boardId = RestAssuredUtils.parseLocation(location);

        Map<String, Object> updateFormData = new HashMap<>();
        updateFormData.put("title", "hello2");
        updateFormData.put("content", "hello3");

        // when
        ExtractableResponse<Response> response = RestAssuredUtils.patch("/board/" + boardId, updateFormData, tokenResponse.getAccessToken(), tokenResponse.getRefreshToken());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
