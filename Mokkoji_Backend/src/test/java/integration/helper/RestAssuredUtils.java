package integration.helper;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.Map;

public class RestAssuredUtils {

    public static final String ACCESS_TOKEN_HEADER_NAME = "access-token";
    public static final String REFRESH_TOKEN_HEADER_NAME = "refresh-token";

    public static ExtractableResponse<Response> get(String url) {
        return RestAssured.given().log().all()
                .when()
                .get(url)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> get(String url, String accessToken, String refreshToken) {
        return RestAssured.given().log().all()
                .header(ACCESS_TOKEN_HEADER_NAME, accessToken)
                .header(REFRESH_TOKEN_HEADER_NAME, refreshToken)
                .when()
                .get(url)
                .then().log().all()
                .extract();
    }

    public static <T> ExtractableResponse<Response> get(String url, T pathParam) {
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get(url, pathParam)
                .then().log().all()
                .extract();
    }

    public static <T> ExtractableResponse<Response> get(String url, T pathParam, String accessToken, String refreshToken) {
        return RestAssured.given().log().all()
                .header(ACCESS_TOKEN_HEADER_NAME, accessToken)
                .header(REFRESH_TOKEN_HEADER_NAME, refreshToken)
                .when()
                .get(url, pathParam)
                .then().log().all()
                .extract();
    }

    public static <T> ExtractableResponse<Response> get(String url, Map<String, T> params) {
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .params(params)
                .when().get(url)
                .then().log().all()
                .extract();
    }

    public static <T> ExtractableResponse<Response> post(String url, T body) {
        return RestAssured.given().log().all()
                .body(body)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post(url)
                .then().log().all()
                .extract();
    }

    public static <T> ExtractableResponse<Response> post(String url, String accessToken, String refreshToken) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(ACCESS_TOKEN_HEADER_NAME, accessToken)
                .header(REFRESH_TOKEN_HEADER_NAME, refreshToken)
                .when()
                .post(url)
                .then().log().all()
                .extract();
    }

    public static <T> ExtractableResponse<Response> post(String url, T pathParam, T body) {
        return RestAssured.given().log().all()
                .body(body)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post(url, pathParam)
                .then().log().all()
                .extract();
    }

    public static <T> ExtractableResponse<Response> post(String url, T body, String accessToken, String refreshToken) {
        return RestAssured.given().log().all()
                .body(body)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(ACCESS_TOKEN_HEADER_NAME, accessToken)
                .header(REFRESH_TOKEN_HEADER_NAME, refreshToken)
                .when()
                .post(url)
                .then().log().all()
                .extract();
    }

    public static <T> ExtractableResponse<Response> post(String url, Map<String, Object> formData, String accessToken, String refreshToken) {
        return RestAssured.given().log().all()
                .formParams(formData)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .header(ACCESS_TOKEN_HEADER_NAME, accessToken)
                .header(REFRESH_TOKEN_HEADER_NAME, refreshToken)
                .when()
                .post(url)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> delete(String url) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete(url)
                .then().log().all()
                .extract();
    }

    public static <T> ExtractableResponse<Response> delete(String url, T pathParam) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete(url, pathParam)
                .then().log().all()
                .extract();
    }

    public static <T> ExtractableResponse<Response> delete(String url, String accessToken, String refreshToken) {
        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(ACCESS_TOKEN_HEADER_NAME, accessToken)
            .header(REFRESH_TOKEN_HEADER_NAME, refreshToken)
            .when()
            .delete(url)
            .then().log().all()
            .extract();
    }

    public static <T> ExtractableResponse<Response> delete(String url, T pathParam, String accessToken, String refreshToken) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(ACCESS_TOKEN_HEADER_NAME, accessToken)
                .header(REFRESH_TOKEN_HEADER_NAME, refreshToken)
                .when()
                .delete(url, pathParam)
                .then().log().all()
                .extract();
    }

    public static <T> ExtractableResponse<Response> put(String url, T body) {
        return RestAssured.given().log().all()
                .body(body)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put(url)
                .then().log().all()
                .extract();
    }

    public static <T> ExtractableResponse<Response> put(String url, T pathParam, T body) {
        return RestAssured.given().log().all()
                .body(body)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put(url, pathParam)
                .then().log().all()
                .extract();
    }

    public static <T> ExtractableResponse<Response> patch(
            String url,
            T pathParam,
            T body,
            String accessToken,
            String refreshToken
    ) {
        return RestAssured.given().log().all()
                .body(body)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(ACCESS_TOKEN_HEADER_NAME, accessToken)
                .header(REFRESH_TOKEN_HEADER_NAME, refreshToken)
                .when()
                .patch(url, pathParam)
                .then().log().all()
                .extract();
    }

    public static <T> ExtractableResponse<Response> patch(String url, T body, String accessToken, String refreshToken) {
        return RestAssured.given().log().all()
                .body(body)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(ACCESS_TOKEN_HEADER_NAME, accessToken)
                .header(REFRESH_TOKEN_HEADER_NAME, refreshToken)
                .when()
                .patch(url)
                .then().log().all()
                .extract();
    }
}
