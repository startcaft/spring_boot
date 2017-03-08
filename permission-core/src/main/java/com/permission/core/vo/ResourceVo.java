package com.permission.core.vo;

import java.util.Date;
import com.permission.core.enums.ResourceEnum;
import com.permission.core.enums.StateEnum;

public class ResourceVo extends IdVo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name; 												
	private String url; 											
	private String description; 										
	private String icon; 												
	private Integer seq; 											
	private ResourceEnum resourcetype; 									
	private StateEnum state; 											
	private Date createdatetime; 		
	
	private Integer pid;
	private String pName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public ResourceEnum getResourcetype() {
		return resourcetype;
	}
	public void setResourcetype(ResourceEnum resourcetype) {
		this.resourcetype = resourcetype;
	}
	public StateEnum getState() {
		return state;
	}
	public void setState(StateEnum state) {
		this.state = state;
	}
	public Date getCreatedatetime() {
		return createdatetime;
	}
	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
}
