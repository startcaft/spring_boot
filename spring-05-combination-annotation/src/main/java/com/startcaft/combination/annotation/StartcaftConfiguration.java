package com.startcaft.combination.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义组合注解实例
 * 组合 {@Configuration #annotationType()} 和 {@Component #annotationType()}
 * @author startcaft
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@ComponentScan
public @interface StartcaftConfiguration {
	
	String[] value() default{};//覆盖 value 参数
}
