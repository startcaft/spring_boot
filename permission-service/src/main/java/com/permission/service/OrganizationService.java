package com.permission.service;

import com.permission.core.vo.NodeTree;
import com.permission.core.vo.OrganizationVo;

public interface OrganizationService {
	
	boolean insertOrg(OrganizationVo vo) throws Exception;
	
	boolean updateOrg(OrganizationVo vo) throws Exception;
	
	OrganizationVo getDetail(Integer orgId) throws Exception;
	
	void recursiveTree(NodeTree node) throws Exception;
	
	/**
	 * 删除指定组织(部门)，如果部门下包含任何一个用户，则删除失败，抛出异常
	 * @param orgId
	 */
	void deleteOrg(Integer orgId) throws Exception;
}
