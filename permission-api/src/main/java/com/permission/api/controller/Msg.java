package com.permission.api.controller;

/**
 * 向ReponseBoby中序列化的数据类
 */
public class Msg {
	
	private String error;
	private boolean isSuccess= false;
	private Object data;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
