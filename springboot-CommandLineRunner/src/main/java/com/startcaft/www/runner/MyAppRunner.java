package com.startcaft.www.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class MyAppRunner implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作 33333333 <<<<<<<<<<<<<");
	}
}
