package integration.helper;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class BoardIntegrationHelper {

	public static ExtractableResponse<Response> addBoard(
		Map<String, Object> formData,
		String accessToken
	) {
		return RestAssuredUtils.post("/board/write", formData, accessToken);
	}

	public static ExtractableResponse<Response> addBoard(
		Map<String, Object> formData,
		String accessToken,
		MultipartFile multipartFile
	) throws IOException {
		return RestAssuredUtils.post("/board/write", formData, multipartFile, accessToken);
	}
}
