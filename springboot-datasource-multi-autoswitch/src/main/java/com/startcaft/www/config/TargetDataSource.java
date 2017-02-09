package com.startcaft.www.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在方法上使用，用于指定使用哪个数据源
 * @author Administrator
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)//注解会被保留到那个阶段，三个值源码级别，编译级别，JVM级别
@Documented//注解会被javadoc工具记录
public @interface TargetDataSource {
	String value();
}
