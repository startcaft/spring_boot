package com.startcaft.www.aspect;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 定义Controller层的日志切面
 * 注解 @Aspect 将一个Java类定义为切面类；
 * 注解 @Pointcut 定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等，
 * 		根据需要在切入点不同位置的切入内容
 * 注解 @Before 在切入点开始处切入内容；
 * 注解 @After 在切入点结尾处切入内容；
 * 注解 @AfterReturing 在切入点 return 内容之后切入内容（可以用来对返回值做一些加工处理）；
 * 注解 @Around 在切入点前后切入内容，并自己控制何时执行切入点自身的内容；
 * 注解 @AfterThrowing 用来处理当切入内容部分抛出异常之后的处理逻辑；
 * @author Administrator
 */
@Aspect
@Component
@Order(-5)
public class WebLogAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
     * 定义一个切入点.
     * 解释下：
     *
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第三个 * 代表任意方法.
     * ~ 第四个 * 定义在web包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
	@Pointcut("execution(public * com.startcaft.*.controller..*.*(..))")
	public void webLog() {}
	
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint){
		//接受到请求，记录请求内容
		logger.info("WebLogAspect.doBefore()");
		ServletRequestAttributes attributes = 
				(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
		//记录下请求内容
		logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        
        //获取所有参数方法
        Enumeration<String> enu = request.getParameterNames();
        while(enu.hasMoreElements()){
        	String paramName = enu.nextElement();
        	System.out.println(paramName+": "+request.getParameter(paramName)); 
        }
	}
	
	@AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinPoint){
      // 处理完请求，返回内容
       logger.info("WebLogAspect.doAfterReturning()");
    }
}
