package com.permission.service;

import com.permission.core.vo.NodeTree;
import com.permission.core.vo.ResourceVo;

public interface ResourceService {
	
	boolean insertResource(ResourceVo vo) throws Exception;
	
	boolean updateResource(ResourceVo vo) throws Exception;
	
	void delete(Integer resourceId) throws Exception;
	
	ResourceVo getDetail(Integer resourceId) throws Exception;
	
	void recursiveTree(NodeTree node,Integer userId) throws Exception;
}
