package com.startcaft.springboot.listener;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * Spring Boot 项目启动异常事件监听类，
 * ApplicationFailedEvent 对象可以获取 Throwable 异常的实例
 * @author startcaft
 *
 */
public class MyApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent> {

	@Override
	public void onApplicationEvent(ApplicationFailedEvent event) {
		Throwable throwable = event.getException();
		handleThrowable(throwable);
	}
	
	/*处理异常*/
    private void handleThrowable(Throwable throwable) {
    }
}
