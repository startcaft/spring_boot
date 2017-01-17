package com.startcaft.mvc.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import com.startcaft.mvc.config.interceptor.DemoInterceptor;
import com.startcaft.mvc.config.messageConverter.SpringMvcMessageConverter;

/**
 * 更多配置请查看 org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
 * 它是 org.springframework.web.servlet.config.annotation.WebMvcConfigurer 接口的实现类
 * @author startcaft
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.startcaft.mvc.config")
public class SpringMvcConfig extends WebMvcConfigurerAdapter {
	
	//定义视图解析器的 Bean
	@Bean
	public InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver viewResolver = 
				new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}
	
	//定义上传文件的视图解析器的 Bean
	@Bean
	public MultipartResolver multipartResolver(){
		CommonsMultipartResolver multipartResolver = 
				new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1000000);
		return multipartResolver;
	}
	
	//定义自定义拦截器的 Bean
	@Bean
	public DemoInterceptor demoInterceptor(){
		return new DemoInterceptor();
	}
	
	//自定义HttpMessageConverter的 Bean
	@Bean
	public SpringMvcMessageConverter conveter(){
		return new SpringMvcMessageConverter();
	}
	
	//Spring MVC 对静态资源的处理
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//addResourceLocations 指定静态资源放置的目录
		//addResourceHandler 指定它们对外暴露的访问路径
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
	}
	
	//Spring MVC 注册拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(demoInterceptor());
	}
	
	//可以注册大量简单的页面转向
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("/index");
		registry.addViewController("/toUpload").setViewName("/upload");
		registry.addViewController("/sse").setViewName("/sse");
	}
	
	//仅仅添加自定义的 HttpMessageConverter，不会覆盖默认注册的 HttpMessageConverter
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(conveter());
	}
	
	/**
	 * 会覆盖掉 Spring MVC 默认注册的多个HttpMessageConverer
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	}
	*/
	
	//Spring MVC 默认路径参数如何带"."的话，"."后面的值将被忽略。
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseRegisteredSuffixPatternMatch(false);
	}
}
