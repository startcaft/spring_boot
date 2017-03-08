package com.permission.core.exception;

/**
 * 自定义异常，捕获Service层的异常时候抛出的自定义异常
 */
public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ServiceException(String msg) {
		super(msg);
	}
}
