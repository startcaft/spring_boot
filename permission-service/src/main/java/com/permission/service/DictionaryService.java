package com.permission.service;


import com.permission.core.vo.DictionaryTypeVo;
import com.permission.core.vo.DictionaryVo;
import com.permission.core.vo.NodeTree;

public interface DictionaryService {
	
	/**
	 * 检查指定的 dicTypeName 是否存在，
	 * @param dicTypeName 不能为空，否则直接返回true
	 * @return true存在/false不存在
	 */
	boolean checkNameExists(String dicTypeName) throws Exception;
	
	/**
	 * 保存数据
	 * @param vo 不能为空，否则直接抛出异常
	 */
	boolean insertRecord(DictionaryTypeVo vo) throws Exception;
	
	/**
	 * 获取指定节点的树状结构
	 * @param node 不能为空
	 */
	public void recursiveTree(NodeTree node) throws Exception;
	
	/**
	 * 获取指定的字典类型
	 * @param id
	 */
	DictionaryTypeVo getById(Integer id) throws Exception;
	
	/**
	 * 更新指定的数据
	 * @param vo 不能为空，却vo中的id不能为空
	 */
	boolean modifyRecord(DictionaryTypeVo vo) throws Exception;
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 添加字典项目
	 * @param vo 字典项vo对象
	 */
	boolean insertDicItem(DictionaryVo vo) throws Exception;
}
