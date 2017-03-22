package com.redis.javabeans;

import java.io.Serializable;

public class UserInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Integer age;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
	@Override
	public int hashCode() {
        return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UserInfo) {
			UserInfo user = (UserInfo) obj;
            return (id.equals(user.id));
        }
        return false;
	}
}
