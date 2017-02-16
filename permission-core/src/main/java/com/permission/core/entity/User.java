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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import com.permission.core.enums.GenderEnum;
import com.permission.core.enums.StateEnum;


@Entity
@Table(name="sys_user")
@DynamicUpdate(true)
public class User extends IdEntity {					//系统用户----业务系统中可以扩展
	
	private String account; 							//账号，非空，唯一，长度30
	private String password; 							//密码，非空，长度200
	private String username; 							//真是姓名，可以为空，长度20
	private GenderEnum gender; 							//性别，非空，长度4，保存数值
	private Date birthday;								//生日，可以为空
	private Date createdatetime; 						//创建时间，非空
	private StateEnum state; 							//状态，非空，长度4，保存数值
	private Organization organization;					//关联的organization对象
	private Set<Role> roles = new HashSet<Role>(0);		//关联的Role集合
	
	@Column(name="user_account",unique=true,nullable=false,length=30)
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	@Column(name="user_password",nullable=false,length=200)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="user_name",length=20)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="user_gender",length=4,nullable=false)
	public GenderEnum getGender() {
		return gender;
	}
	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="user_birthday")
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="create_time")
	public Date getCreatedatetime() {
		return createdatetime;
	}
	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="user_state",length=4,nullable=false)
	public StateEnum getState() {
		return state;
	}
	public void setState(StateEnum state) {
		this.state = state;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_org_id",nullable=false,foreignKey=@ForeignKey(name="permission_foreignKey_reference_org_id"))
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="sys_user_role",
			joinColumns={@JoinColumn(name="user_id",referencedColumnName="id",nullable=false,updatable=false,foreignKey=@ForeignKey(name="permission_foreignKey_userrole_reference_user_id"))},
			inverseJoinColumns={@JoinColumn(name="role_id",referencedColumnName="id",nullable=false,updatable=false,foreignKey=@ForeignKey(name="permission_foreignKey_userrole_reference_role_id"))}
	)
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
