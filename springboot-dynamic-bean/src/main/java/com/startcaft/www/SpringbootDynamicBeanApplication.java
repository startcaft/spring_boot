package com.startcaft.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring 3.0之后，增加了一种新的途径来配置 Bean Definition，就是通过 java代码配置配置 Bean Definition。
 * 与 XML 和 Annotation 两种配置方式不同点在于：
 * 1，XML 和 Annotation 的配置方式为【预定义配置】，
 * 		spring 框架生成对应 Bean 对象。
 * 
 * &&& spring 框架有三个主要的 Hook 类，分别是:
 * 1，org.springframework.context.ApplicationContextAware
 * 		它的 setApplicationContext 方法将在 Spring 启动之前【第一个】被调用。
 * 2，org.springframework.beans.factory.supoort.BeanDefinitionRegistryPostProcessor
 * 		它的 postProcessBeanDefinitionRegistry 方法和 postProcessBeanFactory 方法是【第二和第三个】被调用。
 * 		它们在 Bean 初始化创建之前启动。
 * 3，org.springframework.context.ApplicatioinListener
 * 		用于在初始化完成之后做一些事情，当 spring 所有 xml 或者元注解的 Bean 都被成功创建了，这时会调用它的唯一方法 onApplicationEvent。
 * 
 * @author Administrator
 */
@SpringBootApplication
public class SpringbootDynamicBeanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDynamicBeanApplication.class, args);
	}
}
