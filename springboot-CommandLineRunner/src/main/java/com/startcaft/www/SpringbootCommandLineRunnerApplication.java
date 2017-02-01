package com.startcaft.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootCommandLineRunnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCommandLineRunnerApplication.class, new String[]{"hello,","startcaft"});
	}
}
