package com.startcaft.www.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.Configuration;

import com.startcaft.www.demo.ShanhyA;
import com.startcaft.www.demo.ShanhyB;

/**
 * 实现自己实例化好的 Bean 并注册到 spring 容器中
 * 
 * @author Administrator
 */
@Configuration
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	// bean 的名称生成器
	private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

	/**
	 * postProcessBeanFactory( )是bean配置的工厂方法，在该方法中可以获取到所有在
	 * postProcessBeanDefinitionRegistry() 方法中注册的所有 bean，在这里可以进行属性的设置等操作。
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessor.postProcessBeanFactory()");
		// 这里可以设置属性，例如
		/*
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("dataSourceA");
		MutablePropertyValues mutablePropertyValues = beanDefinition.getPropertyValues();
		// 加入属性.
		mutablePropertyValues.addPropertyValue("driverClassName", "com.mysql.jdbc.Driver");
		mutablePropertyValues.addPropertyValue("url", "jdbc:mysql://localhost:3306/test");
		mutablePropertyValues.addPropertyValue("username", "root");
		mutablePropertyValues.addPropertyValue("password", "123456");
		*/
	}

	/**
	 * 先执行：postProcessBeanDefinitionRegistry()方法，
	 * 在执行：postProcessBeanFactory()方法。
	 */
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		/*
		 * 在这里可以注入bean.
		 */
		registerBean(registry, "shanyA", ShanhyA.class);
		registerBean(registry, "shanyB", ShanhyB.class);
	}

	/**
	 * 提供公共的注册方法。
	 * 
	 * @param beanDefinitionRegistry
	 * @param name
	 * @param beanClass
	 */
	private void registerBean(BeanDefinitionRegistry registry, String string, Class<?> class1) {
		AnnotatedBeanDefinition annotatedBeanDefinition = new AnnotatedGenericBeanDefinition(class1);
		// 可以自动生成name
		String beanName = (string != null ? string
				: this.beanNameGenerator.generateBeanName(annotatedBeanDefinition, registry));
		// bean注册的holer类.
		BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(annotatedBeanDefinition, beanName);
		// 使用bean注册工具类进行注册.
		BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder, registry);
	}
}
