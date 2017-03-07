package com.permission.service;


import java.util.List;
import com.permission.core.queryable.DictionaryQuery;
import com.permission.core.queryable.PageInfo;
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
	 */
	boolean insertDicItem(DictionaryVo vo) throws Exception;
	
	/**
	 * 修改字典项
	 */
	boolean modifyDicItem(DictionaryVo vo) throws Exception;
	
	/**
	 * 获取指定dic_type_id的字典项记录
	 */
	List<DictionaryVo> getByTypeId(Integer typeId) throws Exception;
	
	/**
	 * 获取字典项详细信息(包含一级父节点信息)
	 */
	DictionaryVo getDetail(Integer id) throws Exception;
	
	/**
	 * 分页查询
	 * @param query 查询对象
	 */
	PageInfo<DictionaryVo> pageQuery(DictionaryQuery query) throws Exception;
	
}
