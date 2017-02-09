package com.startcaft.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringbootFilterListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootFilterListenerApplication.class, args);
	}
}
