package com.swagger.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/*
	 * select()函数返回的一个 ApiSelectotBuilder 实例用来控制哪些接口暴露给Swagger来展现。
	 * 本例中指定扫描Controller所在的包路径来定义（除了被 @ApiIgnore 指定的请求）。
	 */
	@Bean
	public Docket createRestApi(){
		{
			return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(this.apiInfo())
					.select()	
					.apis(RequestHandlerSelectors.basePackage("com.swagger.app.controller"))
					.paths(PathSelectors.any())
					.build();
		}
	}
	
	/*
	 * 用来创建Api的基本信息(会在文档页面中展现)
	 */
	private ApiInfo apiInfo(){
		{
			return new ApiInfoBuilder()
					.title("Spring Boot中使用Swagger2构建RESTful APIs")
					.description("详细描述")
					.termsOfServiceUrl("http://www.qq.com")
//					.contact("startcaft")
					.version("1.0")
					.build();
		}
	}
}
