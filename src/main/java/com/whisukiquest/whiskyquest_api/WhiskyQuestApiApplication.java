package com.whisukiquest.whiskyquest_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "ウイスキー管理システム",
		description = "ウイスキーの記録、評価を管理するシステムです。"  ))
@SpringBootApplication
public class WhiskyQuestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhiskyQuestApiApplication.class, args);
	}

}
