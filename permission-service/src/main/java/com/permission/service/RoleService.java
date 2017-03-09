package com.permission.service;

import com.permission.core.queryable.PageInfo;
import com.permission.core.queryable.RoleQuery;
import com.permission.core.vo.RoleVo;

public interface RoleService {
	
	PageInfo<RoleVo> getPage(RoleQuery query) throws Exception;

	void add(RoleVo vo) throws Exception;

	void edit(RoleVo vo) throws Exception;

	RoleVo getDetail(Integer roleId) throws Exception;
	
	/**
	 * 为一个指定的角色授权
	 * @param role 角色ID
	 */
	void grant(RoleVo vo) throws Exception;
}
