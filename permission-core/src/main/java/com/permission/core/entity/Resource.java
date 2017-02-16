package com.permission.core.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import com.permission.core.enums.ResourceEnum;
import com.permission.core.enums.StateEnum;


@Entity
@Table(name="sys_resource")
@DynamicUpdate(true)
public class Resource extends IdEntity {
	
	private String name; 												//资源名称，非空，唯一，长度50
	private String url; 												//资源路径，长度200，
	private String description; 										//描述，数据库字段类型text
	private String icon; 												//图标，长度200
	private Integer seq; 												//排序，长度4
	private ResourceEnum resourcetype; 									//资源类型，非空，长度4，实际保存数值
	private StateEnum state; 											//状态，非空，长度4，实际保存数值
	private Date createdatetime; 										//创建时间，非空
	private Resource parentResource; 									//关联的父节点对象
	private Set<Resource> childResources = new HashSet<Resource>(0);	//关联的子节点集合
	private Set<Role> roles = new HashSet<Role>(0);						//关联的Role集合
	
	@Column(name="resource_name",nullable=false,unique=true,length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="resource_url",length=200)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name="resource_desc",columnDefinition="text")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="resource_icon",length=200)
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Column(name="resource_seq",length=4)
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="resource_type",nullable=false,length=4)
	public ResourceEnum getResourcetype() {
		return resourcetype;
	}
	public void setResourcetype(ResourceEnum resourcetype) {
		this.resourcetype = resourcetype;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="resource_state",nullable=false,length=4)
	public StateEnum getState() {
		return state;
	}
	public void setState(StateEnum state) {
		this.state = state;
	}
	
	@Temporal(TemporalType.TIME)
	@Column(name="create_time")
	public Date getCreatedatetime() {
		return createdatetime;
	}
	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pid",foreignKey=@ForeignKey(name="permission_foreignKey_reference_slef_resource_id"))
	public Resource getParentResource() {
		return parentResource;
	}
	public void setParentResource(Resource parentResource) {
		this.parentResource = parentResource;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="parentResource")
	public Set<Resource> getChildResources() {
		return childResources;
	}
	public void setChildResources(Set<Resource> childResources) {
		this.childResources = childResources;
	}
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="sys_role_resource", 
			joinColumns={@JoinColumn(name="resource_id", nullable=false, updatable=false,foreignKey=@ForeignKey(name="permission_foreignKey_roleresource_reference_resource_id"))}, 
			inverseJoinColumns = {@JoinColumn(name="role_id", nullable=false, updatable=false,foreignKey=@ForeignKey(name="permission_foreignKey_roleresource_reference_role_id"))}
	)
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
