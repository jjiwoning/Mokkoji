package com.ssafy.Mokkoji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MokkojiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MokkojiApplication.class, args);
	}

}
