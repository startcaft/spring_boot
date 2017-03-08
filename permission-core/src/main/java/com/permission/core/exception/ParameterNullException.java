package com.permission.core.exception;

/**
 * 自定义异常：标识方法入参为空
 */
public class ParameterNullException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ParameterNullException(String paramterName,Class<?> paramterClass) {
		super("参数类型：" + paramterClass.getSimpleName() + "的参数：" + paramterName + "不能为空");
	}
}
