package com.permission.service;

import com.permission.core.vo.NodeTree;
import com.permission.core.vo.OrganizationVo;

public interface OrganizationService {
	
	boolean insertOrg(OrganizationVo vo) throws Exception;
	
	boolean updateOrg(OrganizationVo vo) throws Exception;
	
	OrganizationVo getDetail(Integer orgId) throws Exception;
	
	void recursiveTree(NodeTree node) throws Exception;
}
