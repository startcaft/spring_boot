package com.permission.core.exception;

public class RecordExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordExistException(String EntityPropertyName) {
		super("指定的" + EntityPropertyName + "已经存在");
	}
}
