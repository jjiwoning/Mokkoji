package integration.helper;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class BoardIntegrationHelper {

	public static ExtractableResponse<Response> addBoard(
		Map<String, Object> formData,
		String accessToken,
		String refreshToken
	) {
		return RestAssuredUtils.post("/board/write", formData, accessToken, refreshToken);
	}

	public static ExtractableResponse<Response> addBoard(
		Map<String, Object> formData,
		String accessToken,
		String refreshToken,
		MultipartFile multipartFile
	) throws IOException {
		return RestAssuredUtils.post("/board/write", formData, multipartFile, accessToken, refreshToken);
	}
}
