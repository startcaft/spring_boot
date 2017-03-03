package com.permission.core.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 通用树状结构
 */
public final class NodeTree {
	
	private Integer id;													//节点
	private Integer pid;												//父节点
	private String name;												//名称
	private List<NodeTree> children = new ArrayList<NodeTree>();		//子节点
	private Map<String,Object> attributes;								//自定义属性
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	
	public NodeTree() {
		super();
	}
	
	public NodeTree(Integer id, Integer pid, String name) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "NodeTree [id=" + id + ", pid=" + pid + ", name=" + name + ", children=" + children + ", attributes="
				+ attributes + "]";
	}
}
