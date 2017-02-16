package com.permission.core.vo;

import java.util.List;

public class RoleVo implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name; 											
	private Integer seq; 											
	private String description; 	
	
	private List<Integer> resourceIds;
	private List<String> resourceNames;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Integer> getResourceIds() {
		return resourceIds;
	}
	public void setResourceIds(List<Integer> resourceIds) {
		this.resourceIds = resourceIds;
	}
	public List<String> getResourceNames() {
		return resourceNames;
	}
	public void setResourceNames(List<String> resourceNames) {
		this.resourceNames = resourceNames;
	}
}
