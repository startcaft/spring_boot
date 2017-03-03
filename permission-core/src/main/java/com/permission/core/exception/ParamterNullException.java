package com.permission.core.exception;

public class ParamterNullException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ParamterNullException(String paramterName,Class<?> paramterClass) {
		super("参数类型：" + paramterClass.getSimpleName() + "的参数：" + paramterName + "不能为空");
	}
}
