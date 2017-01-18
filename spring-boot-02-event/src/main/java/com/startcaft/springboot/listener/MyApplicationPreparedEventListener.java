package com.startcaft.springboot.listener;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Spring Boot 上下文创建后执行的事件监听类，
 * ApplicationPreparedEvent 可以获取 ConfigurableApplicationContext 上下文对象，
 * 但是在这无法获取自定义的 Bean 来进行操作。
 * @author startcaft
 *
 */
public class MyApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {

	@Override
	public void onApplicationEvent(ApplicationPreparedEvent event) {
		
		ConfigurableApplicationContext applicationContext = event.getApplicationContext();
		passContextInfo(applicationContext);
	}

	 /**
     * 传递上下文
     * @param cac
     */
	private void passContextInfo(ConfigurableApplicationContext applicationContext) {
		//dosomething()
	}
}
