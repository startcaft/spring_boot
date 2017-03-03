package com.permission.core.vo;

import java.util.List;
import java.util.Map;

/**
 * 通用树状结构
 */
public class NodeTree extends IdVo {
	
	private Integer pid;							//父节点
	private String name;							//名称
	private List<NodeTree> children;				//子节点
	private Map<String,Object> attributes;			//自定义属性
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<NodeTree> getChildren() {
		return children;
	}
	public void setChildren(List<NodeTree> children) {
		this.children = children;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
}
