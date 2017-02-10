package com.startcaft.www;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootLogLogbackApplication {
	
	//实例化 slf4j 中的 Logger 对象
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootLogLogbackApplication.class);

	public static void main(String[] args) {
		
		logTest();
		
		SpringApplication.run(SpringbootLogLogbackApplication.class, args);
	}
	
	public static void logTest(){
		LOGGER.debug("日志输出测试 Debug");
		LOGGER.trace("日志输出测试 Trace");
		LOGGER.info("日志输出测试 Info");
	}
}
