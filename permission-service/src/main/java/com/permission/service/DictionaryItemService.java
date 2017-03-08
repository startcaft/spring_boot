package com.permission.service;

import java.util.List;

import com.permission.core.queryable.DictionaryQuery;
import com.permission.core.queryable.PageInfo;
import com.permission.core.vo.DictionaryVo;

public interface DictionaryItemService {
	
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
