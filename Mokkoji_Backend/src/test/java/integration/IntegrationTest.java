package integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

import com.ssafy.Mokkoji.MokkojiApplication;

import integration.config.RedisTestContainerConfig;
import integration.helper.DatabaseCleaner;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = {MokkojiApplication.class, DatabaseCleaner.class})
@Import(RedisTestContainerConfig.class)
public class IntegrationTest {

	@LocalServerPort
	int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@BeforeEach
	public void setUp() {
		RestAssured.port = port;
	}

	@AfterEach
	public void tearDown() {
		databaseCleaner.tableClear();
	}
}
