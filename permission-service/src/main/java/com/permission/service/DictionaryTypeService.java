package com.permission.service;

import com.permission.core.vo.DictionaryTypeVo;
import com.permission.core.vo.NodeTree;

public interface DictionaryTypeService {
	
	/**
	 * 保存数据
	 * @param vo 不能为空，否则直接抛出异常
	 */
	void insertRecord(DictionaryTypeVo vo) throws Exception;
	
	/**
	 * 获取指定节点的树状结构
	 * @param node 不能为空
	 */
	void getDicTypeTree(NodeTree node) throws Exception;
	
	/**
	 * 获取指定的字典类型，没有数据则返回null
	 * @param id
	 */
	DictionaryTypeVo getById(Integer id) throws Exception;
	
	/**
	 * 更新指定的数据
	 * @param vo 不能为空，却vo中的id不能为空
	 */
	void modifyRecord(DictionaryTypeVo vo) throws Exception;
}
