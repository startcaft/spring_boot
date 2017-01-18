package com.startcaft.springboot.listener;

import java.util.Iterator;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * Spring Boot 配置环境事件监听类，
 * ApplicationEnvironmentPreparedEvent 可以获取到 ConfigurableEnvironment 接口实例
 * @author startcaft
 *
 */
public class MyApplicationEnvironmentPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
	
	@Override
	public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
		
		ConfigurableEnvironment env = event.getEnvironment();
		MutablePropertySources mps = env.getPropertySources();
		if (mps != null) {
			Iterator<PropertySource<?>> iter = mps.iterator();
			while(iter.hasNext()){
				PropertySource<?> ps = iter.next();
				System.out.println("ps.getName:{" + ps.getName() + "};"
						+ "ps.getSource():{" + ps.getSource() + "};"
								+ "ps.getClass:{" + ps.getClass() + "}");
			}
		}
	}
}
