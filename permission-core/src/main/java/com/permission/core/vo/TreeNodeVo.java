package com.permission.core.vo;

import java.util.List;
import java.util.Map;

/**
 * 通用树状数据结构
 */
public class TreeNodeVo {
	
	private Integer id;									//id
	private Integer pId;								//pId
	private String name;								//显示文本
	private Integer level;								//层级，自动计算
	private boolean isLeaf = false;						//是否叶节点，默认false（叶节点就是没有子节点的节点）
	private Map<String,Object> attributes;				//其他自定义属性，根据实际情况自己加
	private List<TreeNodeVo> childrens;					//包含的子节点，递归处理。
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public boolean isLeaf() {
		return isLeaf;
	}
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public List<TreeNodeVo> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<TreeNodeVo> childrens) {
		this.childrens = childrens;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
}
