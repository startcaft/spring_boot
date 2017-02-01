package com.startcaft.www.runner;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 实际应用中，会在 项目服务启动的时候就去加载一些数据或者做一些事情。
 * 为了解决这样的问题，spring boot 提供了 CommandLineRunner 接口(或者ApplicationRunner接口)，并实现其 run 方法。
 * run 方法中的参数args 是 启动类传递过来的。
 * 
 * 还可以使用 @Order 注解或者实现 Order 接口来规定所有的Runner实力的运行顺序。
 * 
 * Runner 有点类似于 java web 项目的 application context 应用程序上下文。
 * 
 * Spring Boot 项目启动时，【会遍历 xxxRunner 接口实例的 Bean】，然后根据 Order 的排序值从小到大顺序执行，没有指定则最后执行。
 * 
 * @author Administrator
 *
 */
@Component
public class MyStartupRunner1 implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println(args[1]);
		System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作 11111111 <<<<<<<<<<<<<");
	}

}
