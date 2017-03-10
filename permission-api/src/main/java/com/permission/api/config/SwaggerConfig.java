package com.permission.api.config;

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
 * Swagger 环境配置
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
					.apis(RequestHandlerSelectors.basePackage("com.permission.api.controller"))
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
					.title("后台管理权限系统RESTful APIs")
					.description("主要用来配置用户，组织(部门)，数据字典，系统资源，角色以及权限数据")
//					.contact("startcaft")
					.version("0.0.1-SNAPSHOT")
					.build();
		}
	}
}
