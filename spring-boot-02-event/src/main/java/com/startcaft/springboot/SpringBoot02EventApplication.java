package com.startcaft.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.startcaft.springboot.listener.MyApplicationEnvironmentPreparedEventListener;
import com.startcaft.springboot.listener.MyApplicationFailedEventListener;
import com.startcaft.springboot.listener.MyApplicationPreparedEventListener;
import com.startcaft.springboot.listener.MyApplicationStartedEventListener;

@SpringBootApplication
public class SpringBoot02EventApplication {

	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(SpringBoot02EventApplication.class);
		//为 app 绑定一个 ApplicationListener<ApplicationStartedEvent> 对象
		app.addListeners(new MyApplicationStartedEventListener());
		//为 app 绑定一个 ApplicationListener<ApplicationEnvironmentPreparedEvent> 对象
		app.addListeners(new MyApplicationEnvironmentPreparedEventListener());
		//为 app 绑定一个 ApplicationListener<ApplicationPreparedEvent> 对象
		app.addListeners(new MyApplicationPreparedEventListener());
		//为 app 绑定一个 ApplicationListener<ApplicationFailedEvent> 对象
		app.addListeners(new MyApplicationFailedEventListener());
		//运行 app
		app.run(args);
	}
}
