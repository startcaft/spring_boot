package com.startcaft.www.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * spring boot 注册 Filter 的步骤跟Serlvet一样；
 * @author Administrator
 */
@WebFilter(urlPatterns="/*",filterName="myfilter")
public class MyFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("过滤器初始化");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("执行过滤操作");
        chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("过滤器销毁");
	}
}
