package com.permission.service;

import com.permission.core.vo.DictionaryTypeVo;

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
}
