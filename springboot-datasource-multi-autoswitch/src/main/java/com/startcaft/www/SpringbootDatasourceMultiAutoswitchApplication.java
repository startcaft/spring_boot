package com.startcaft.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.startcaft.www.config.DynamicDataSourceRegister;

@SpringBootApplication
//注册动态多数据源
@Import({DynamicDataSourceRegister.class})
public class SpringbootDatasourceMultiAutoswitchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDatasourceMultiAutoswitchApplication.class, args);
	}
}
