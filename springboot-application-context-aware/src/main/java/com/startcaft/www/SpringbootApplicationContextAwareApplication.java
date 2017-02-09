package com.startcaft.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.startcaft.www.util.SpringUtil;

@SpringBootApplication
@Import(SpringUtil.class)
public class SpringbootApplicationContextAwareApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplicationContextAwareApplication.class, args);
	}
	
//	@Bean
//    public SpringUtil springUtil2(){
//		return new SpringUtil();
//	}
}
