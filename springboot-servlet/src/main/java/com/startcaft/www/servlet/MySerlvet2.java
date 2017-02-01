package com.startcaft.www.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * spring boot 注册自定义 Servlet的第二种方式：
 * 通过@WebServlet、@WebFilter、@WebListener,
 * 然后在 spring boot 的XXXApplication类上使用注解 @ServletComponentScan 来注册。
 * @author Administrator
 */
@WebServlet(urlPatterns="/myservlet2",description="注解式Serlvet的说明")
public class MySerlvet2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">>>>>>>>>>MySerlvet2$doGet()<<<<<<<<<<<");
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
		out.println("<h1>这是：myServlet2</h1>");
		out.println("</body>");
		out.println("</html>");
	}
}
