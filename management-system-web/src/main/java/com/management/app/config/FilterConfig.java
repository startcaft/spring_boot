package com.management.app.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class FilterConfig {
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	//												Druid监控配置相关的Filter
	//
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Bean
	public FilterRegistrationBean druidStatFilter() {
		{
			FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
			// 添加过滤规则.
			filterRegistrationBean.addUrlPatterns("/*");

			// 添加不需要忽略的格式信息.
			filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
			return filterRegistrationBean;
		}
	}
}
