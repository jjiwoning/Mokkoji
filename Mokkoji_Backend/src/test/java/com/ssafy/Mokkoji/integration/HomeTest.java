package com.ssafy.Mokkoji.integration;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.operation.preprocess.Preprocessors;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@DisplayName("홈 컨트롤러 인수 테스트")
@ExtendWith(RestDocumentationExtension.class)
public class HomeTest extends IntegrationTest {

    private RequestSpecification spec;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider provider) {
        super.setUp();
        this.spec = new RequestSpecBuilder() // 2.spec setUp
                .addFilter(documentationConfiguration(provider))
                .build();
    }

    @Test
    @DisplayName("홈 화면 get")
    void test1() {
        ExtractableResponse<Response> response = RestAssured
                .given(this.spec).log().all()
                .contentType(ContentType.JSON)
                .filter(document(
                                "home",
                                preprocessRequest(Preprocessors.prettyPrint()),
                                preprocessResponse(Preprocessors.prettyPrint()),
                                responseFields(
                                        fieldWithPath("status").description("응답 상태")
                                )
                        )
                )
                .when().get("/")
                .then().log().all()
                .extract();
    }

}
