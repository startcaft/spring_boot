package com.startcaft.www.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;

/**
 * 普通类调用 Spring 容器中的 Bean 对象 说明： 1，此类需要放到 spring boot 启动类同包或者子包下才能被扫描到，否则无效。
 * $$$ 如果不想使用 @Component 注解，可以采用在 spring boot 类中使用 @Bean 注解来注册SpringUtil $$$
 * $$$ 或者注解在 spring boot 启动类中使用 @Import 注解来导入SpringUtil.class  $$$
 * @author Administrator
 */
//@Component
public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringUtil.applicationContext == null) {
			SpringUtil.applicationContext = applicationContext;
		}
		System.out.println("---------------------------------------------------------------------");
		System.out.println(
				"---------------com.startcaft.www.util.SpringUtil------------------------------------------------------");
		System.out.println(
				"========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象,applicationContext="
						+ SpringUtil.applicationContext + "========");
		System.out.println("---------------------------------------------------------------------");
	}
	
	/**
	 * 获取 ApplicationContext 实例
	 * @return
	 */
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	
	/**
	 * 通过Bean的name获取 Bean 实例
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName){
		return getApplicationContext().getBean(beanName);
	}
	
	/**
	 * 通过 Bean 的class 获取 Bean 实例
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz){
		return getApplicationContext().getBean(clazz);
	}
	
	/**
	 * 通过name,以及Clazz返回指定的Bean
	 * @param name
	 * @param clazz
	 * @return
	 */
    public static <T> T getBean(String name,Class<T> clazz){
       return getApplicationContext().getBean(name, clazz);
    }
}
