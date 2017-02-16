package com.permission.core.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="sys_organization")
@DynamicUpdate(true)
public class Organization extends IdEntity {								//组织结构(部门)
	
	private String name;													//名称，唯一，不能为空，长度30	
	private String code;													//编码，可以为空，长度20
	private String icon;													//图标url，可以为空，长度200
	private Integer seq;													//排序，可以为空，长度4
	private String address;													//地址，可以为空，长度100
	private Date createTime;												//创建时间，精确到日期，非空
	private Organization parentOrg;											//关联的0~1个父节点
	private Set<Organization> childOrgs = new HashSet<Organization>();		//关联的0~N个子节点
	
	@Column(name="org_name",nullable=false,unique=true,length=30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="org_code",length=20)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="org_icon_url",length=200)
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Column(name="org_seq",length=4)
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	@Column(name="org_address",length=100)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="org_create_time",nullable=false)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pid",foreignKey=@ForeignKey(name="permission_foreignKey_reference_self_org_id"))
	public Organization getParentOrg() {
		return parentOrg;
	}
	public void setParentOrg(Organization parentOrg) {
		this.parentOrg = parentOrg;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="parentOrg")
	public Set<Organization> getChildOrgs() {
		return childOrgs;
	}
	public void setChildOrgs(Set<Organization> childOrgs) {
		this.childOrgs = childOrgs;
	}
}
