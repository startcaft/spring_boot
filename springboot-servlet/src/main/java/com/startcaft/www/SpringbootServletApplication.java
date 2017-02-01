package com.startcaft.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import com.startcaft.www.servlet.MyServlet;

@SpringBootApplication
@ServletComponentScan
public class SpringbootServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServletApplication.class, args);
	}
	
	@Bean
	public ServletRegistrationBean MySerlvet(){
		return new ServletRegistrationBean(new MyServlet(), "/myserlvet");
	}
}
