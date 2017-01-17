package com.startcaft.mvc.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * org.springframework.web.WebApplicationInitializer 是 Spring 提供用来配置 Servlet 3.0+配置的接口，
 * 从而实现了替代 web.xml的位置。实现此接口将会自动被 SpringServletContainerInitializer（用来启动 Servlet 3.0 容器）获取到。
 */
public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		//新建 WebApplicationContext，Web环境下的上下文对象
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		//注册配置类
		webContext.register(SpringMvcConfig.class);
		//关联Web上下文和当前servletContext
		webContext.setServletContext(servletContext);
		
		//配置SpringMVC的前端控制器
		Dynamic mvcServlet = servletContext.addServlet("dispathcer", new DispatcherServlet(webContext));
		mvcServlet.addMapping("/");
		mvcServlet.setLoadOnStartup(1);
	}

}
