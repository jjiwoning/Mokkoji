package integration.board;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import com.ssafy.Mokkoji.config.FileStoreTestConfig;
import com.ssafy.Mokkoji.core.user.dto.response.AccessTokenResponse;

import integration.IntegrationTest;
import integration.helper.BoardIntegrationHelper;
import integration.helper.RestAssuredUtils;
import integration.helper.UserIntegrationHelper;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

@DisplayName("Board 인수 테스트")
@Import(FileStoreTestConfig.class)
class BoardIntegrationTest extends IntegrationTest {

	@Test
	@DisplayName("게시글을 등록할 수 있다.")
	void write() {
		// given
		AccessTokenResponse accessTokenResponse = UserIntegrationHelper.signupAndLogin();

		Map<String, Object> formData = new HashMap<>();
		formData.put("title", "hello");
		formData.put("content", "hello");

		// when
		ExtractableResponse<Response> response = BoardIntegrationHelper.addBoard(formData,
			accessTokenResponse.getAccessToken());

		// then
		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	@DisplayName("이미지를 포함하여 게시글을 등록할 수 있다.")
	void writeWithImage() throws IOException {
		// given
		AccessTokenResponse accessTokenResponse = UserIntegrationHelper.signupAndLogin();

		Map<String, Object> formData = new HashMap<>();
		formData.put("title", "hello");
		formData.put("content", "hello");

		MockMultipartFile multipartFile = new MockMultipartFile("image.png", "test.png", MediaType.IMAGE_GIF_VALUE,
			"test".getBytes());

		// when
		ExtractableResponse<Response> response = BoardIntegrationHelper.addBoard(formData,
			accessTokenResponse.getAccessToken(), multipartFile);

		// then
		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	@DisplayName("게시글을 수정할 수 있다.")
	void getImage() throws IOException {
		// given
		AccessTokenResponse accessTokenResponse = UserIntegrationHelper.signupAndLogin();

		Map<String, Object> formData = new HashMap<>();
		formData.put("title", "hello");
		formData.put("content", "hello");

		MockMultipartFile multipartFile = new MockMultipartFile("image.png", "test.png", MediaType.IMAGE_GIF_VALUE,
			"test".getBytes());

		String location = BoardIntegrationHelper.addBoard(formData, accessTokenResponse.getAccessToken(), multipartFile)
			.header("location");
		Long boardId = RestAssuredUtils.parseLocation(location);

		Map<String, Object> updateFormData = new HashMap<>();
		updateFormData.put("title", "hello2");
		updateFormData.put("content", "hello3");

		// when
		ExtractableResponse<Response> response = RestAssuredUtils.patch("/board/" + boardId, updateFormData,
			accessTokenResponse.getAccessToken());

		// then
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}
}
