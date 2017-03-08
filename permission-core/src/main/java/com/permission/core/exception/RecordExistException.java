package com.permission.core.exception;

/**
 * 自定义异常，标识指定的字段值已经存在，用于检测唯一的字段是否存在。
 */
public class RecordExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordExistException(String EntityPropertyName) {
		super("指定的" + EntityPropertyName + "已经存在");
	}
}
