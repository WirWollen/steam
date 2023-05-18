package com.parse.steam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SteamApplication {

	public static void main(String[] args) {
		SpringApplication.run(SteamApplication.class, args);
	}

}
