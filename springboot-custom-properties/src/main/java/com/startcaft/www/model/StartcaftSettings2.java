package com.startcaft.www.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 沿用 spring boot 提供的 application.propeties 文件，只不过使用自定义的属性
 * 使用注解 @ConfigurationProperties(prefix="属性前缀")，绑定到一个模型类。
 * 
 * @author Administrator
 */
@ConfigurationProperties(prefix="startcaft",locations="classpath:startcaft.properties")
public class StartcaftSettings2 {
	
	private Integer userid;
	private String username;
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
