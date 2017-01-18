package com.startcaft.springboot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * Spring Boot 项目的启动监听类,
 * ApplicationStartedEvent 可以获取到 SpringApplication 实例，可以做一些执行前的设置
 * 
 * @author startcaft
 */
public class MyApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {
	
	private Logger logger = LoggerFactory.getLogger(MyApplicationStartedEventListener.class);
	
	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		
		SpringApplication app = event.getSpringApplication();
		//不显示banner信息
		app.setBannerMode(Mode.OFF);
		logger.info("==MyApplicationStartedEventListener==");
	}
}
