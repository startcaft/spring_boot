package com.permission.core.queryable;

/**
 * Role实体的查询对象
 */
public class RoleQuery extends PageRequest {
	
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
