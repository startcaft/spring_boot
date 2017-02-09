package com.startcaft.www.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * spring boot 注册自定义Serlvet的第一种方式：
 * 代码注册---通过ServletRegistrationBean，FilterRegistrationBean，ServletListenerRegistratioinBean
 * 分别用来注册 Servlet，Filter和Listener；
 * 
 * @author Administrator
 */
public class MyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(">>>>>>>>>>MyServlet$doGet()<<<<<<<<<<<");
        doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(">>>>>>>>>>doPost()<<<<<<<<<<<");
        resp.setContentType("text/html"); 
        PrintWriter out = resp.getWriter(); 
        out.println("<html>"); 
        out.println("<head>"); 
        out.println("<title>Hello World</title>"); 
        out.println("</head>"); 
        out.println("<body>"); 
        out.println("<h1>这是：MyServlet</h1>"); 
        out.println("</body>"); 
        out.println("</html>");
	}
}
