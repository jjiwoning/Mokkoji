package integration.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class RedisTestContainerConfig {

	static {
		final GenericContainer<?> REDIS_CONTAINER =
			new GenericContainer<>(DockerImageName.parse("redis:latest"))
				.withExposedPorts(6379);
		REDIS_CONTAINER.start();

		System.setProperty("spring.redis.host", REDIS_CONTAINER.getHost());
		System.setProperty("spring.redis.port",
			String.valueOf(REDIS_CONTAINER.getFirstMappedPort()));
	}
}
