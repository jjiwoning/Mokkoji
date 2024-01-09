package integration.helper;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class RestAssuredUtils {

	public static ExtractableResponse<Response> get(String url) {
		return RestAssured.given().log().all()
			.when()
			.get(url)
			.then().log().all()
			.extract();
	}

	public static ExtractableResponse<Response> get(String url, String accessToken) {
		return RestAssured.given().log().all()
			.header(HttpHeaders.AUTHORIZATION, accessToken)
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

	public static <T> ExtractableResponse<Response> get(String url, T pathParam, String accessToken) {
		return RestAssured.given().log().all()
			.header(HttpHeaders.AUTHORIZATION, accessToken)
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

	public static <T> ExtractableResponse<Response> post(String url, String accessToken) {
		return RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.AUTHORIZATION, accessToken)
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

	public static <T> ExtractableResponse<Response> post(String url, T body, String accessToken) {
		return RestAssured.given().log().all()
			.body(body)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.AUTHORIZATION, accessToken)
			.when()
			.post(url)
			.then().log().all()
			.extract();
	}

	public static <T> ExtractableResponse<Response> post(String url, Map<String, Object> formData, String accessToken) {
		return RestAssured.given().log().all()
			.formParams(formData)
			.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
			.header(HttpHeaders.AUTHORIZATION, accessToken)
			.when()
			.post(url)
			.then().log().all()
			.extract();
	}

	public static <T> ExtractableResponse<Response> post(
		String url,
		Map<String, Object> formData,
		MultipartFile file,
		String accessToken
	) throws IOException {
		return RestAssured.given().log().all()
			.formParams(formData)
			.multiPart("images", "images", file.getBytes(), "image/png")
			.header(HttpHeaders.AUTHORIZATION, accessToken)
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

	public static <T> ExtractableResponse<Response> delete(String url, String accessToken) {
		return RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.AUTHORIZATION, accessToken)
			.when()
			.delete(url)
			.then().log().all()
			.extract();
	}

	public static <T> ExtractableResponse<Response> delete(String url, T pathParam, String accessToken) {
		return RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.AUTHORIZATION, accessToken)
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
		String accessToken
	) {
		return RestAssured.given().log().all()
			.body(body)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.AUTHORIZATION, accessToken)
			.when()
			.patch(url, pathParam)
			.then().log().all()
			.extract();
	}

	public static <T> ExtractableResponse<Response> patch(String url, T body, String accessToken) {
		return RestAssured.given().log().all()
			.body(body)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.AUTHORIZATION, accessToken)
			.when()
			.patch(url)
			.then().log().all()
			.extract();
	}

	public static <T> ExtractableResponse<Response> patch(String url, Map<String, Object> formData,
		String accessToken) throws IOException {
		return RestAssured.given().log().all()
			.formParams(formData)
			.header(HttpHeaders.AUTHORIZATION, accessToken)
			.when()
			.patch(url)
			.then().log().all()
			.extract();
	}

	public static Long parseLocation(String location) {
		String[] parts = location.split("/");
		return Long.parseLong(parts[parts.length - 1]);
	}
}
