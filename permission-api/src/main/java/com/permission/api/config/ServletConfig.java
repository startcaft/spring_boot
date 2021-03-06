package com.permission.api.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.support.http.StatViewServlet;

@Configuration
public class ServletConfig {
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	//												Druid监控配置相关的Servlet
	//												监控首页：${contextPath}/druid/index.html
	//
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Bean
	public ServletRegistrationBean DruidStatViewServle() {
		{
			ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
					"/druid/*");
			// 添加初始化参数：initParams
			// 白名单：
			servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
			// IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not
			// permitted to view this page.
			servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
			// 登录查看信息的账号密码.
			servletRegistrationBean.addInitParameter("loginUsername", "dbadmin");
			servletRegistrationBean.addInitParameter("loginPassword", "dbadmin");
			// 是否能够重置数据.
			servletRegistrationBean.addInitParameter("resetEnable", "false");
			return servletRegistrationBean;
		}
	}
	
	
}	
