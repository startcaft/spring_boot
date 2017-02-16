package com.permission.core.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="sys_role")
public class Role extends IdEntity {								//系统角色
	
	private String name; 											//角色名称，非空，唯一，长度30
	private Integer seq; 											//排序，长度4
	private String description; 									//备注，数据库字段类型 text
	private Set<Resource> resources = new HashSet<Resource>(0);		//关联的Resource集合
	private Set<User> users = new HashSet<User>(0);					//关联的User集合
	
	@Column(name="role_name",nullable=false,unique=true,length=40)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="role_seq",length=4)
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	@Column(name="role_desc",columnDefinition="text")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToMany(fetch=FetchType.LAZY,mappedBy="roles")
	public Set<Resource> getResources() {
		return resources;
	}
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	
	@ManyToMany(fetch=FetchType.LAZY,mappedBy="roles")
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
