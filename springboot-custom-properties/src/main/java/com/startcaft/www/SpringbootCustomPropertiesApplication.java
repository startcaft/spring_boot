package com.startcaft.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.startcaft.www.model.StartcaftSettings;
import com.startcaft.www.model.StartcaftSettings2;

/**
 * 使用自定义属性，最后需要在 xxxApplication 类上加上注解 @EnableConfigurationProperties，并把对应的模型类的类型当成其参数。
 * @author Administrator
 *
 */
@SpringBootApplication
@EnableConfigurationProperties({StartcaftSettings.class,StartcaftSettings2.class})
public class SpringbootCustomPropertiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCustomPropertiesApplication.class, args);
	}
}
