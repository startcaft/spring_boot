package com.permission.api.controller;

/**
 * 向ReponseBoby中序列化的数据类
 */
public class JSONResult {
	
	private String tipInfo;
	private boolean isSuccess= false;
	private Object response;
	
	
	public String getTipInfo() {
		return tipInfo;
	}
	public void setTipInfo(String tipInfo) {
		this.tipInfo = tipInfo;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
}
