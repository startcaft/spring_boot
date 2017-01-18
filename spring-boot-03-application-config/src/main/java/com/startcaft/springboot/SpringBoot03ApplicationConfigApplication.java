package com.startcaft.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBoot03ApplicationConfigApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringBoot03ApplicationConfigApplication.class);
		//app.setAdditionalProfiles("dev");
		app.run(args);
	}
}
