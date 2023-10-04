package integration.helper;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.util.Map;

public class BoardHelper {

    public static ExtractableResponse<Response> addBoard(
            Map<String, Object> formData,
            String accessToken,
            String refreshToken
    ) {
        return RestAssuredUtils.post("/board/write", formData, accessToken, refreshToken);
    }
}
