package com.permission.service.impl;

/**
 * Service抽象类，一些公用的字段或方法
 */
public abstract class BaseService {
	
	/**
	 * 指定cache name
	 */
	protected static final String CACHE_NAME = "jpaCache";
	
	/**
	 * 为DictionaryType缓存时的key的前缀，
	 * 这里的单引号不能少，否则会报错，被识别为一个对象
	 */
	protected static final String DIC_TYPE_CACHE_KEY_PREFIX = "'dic_type_'";
}
