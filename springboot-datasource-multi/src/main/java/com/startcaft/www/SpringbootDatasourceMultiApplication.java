package com.startcaft.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 在 spring boot 中使用多数据源的步骤：
 * 1，在配置文件中配置 数据源的数量，动态创建 dataSource 并注册到 spring 中。
 * @author Administrator
 *
 */
@SpringBootApplication
public class SpringbootDatasourceMultiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDatasourceMultiApplication.class, args);
	}
}
