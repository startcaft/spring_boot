package com.permission.service.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import com.permission.core.exception.ParamterNullException;
import com.permission.core.queryable.PageRequest;



/**
 * Service抽象类，一些公用的字段或方法
 */
public abstract class BaseService {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseService.class);
	
	/**
	 * 指定cache name
	 */
	protected static final String CACHE_NAME = "jpaCache";
	
//	/**
//	 * 为DictionaryType缓存时的key的前缀，
//	 * 这里的单引号不能少，否则会报错，被识别为一个对象
//	 */
//	protected static final String DIC_TYPE_CACHE_KEY_PREFIX = "'dic_type_'";
	
	/**
	 * 构建Pageable实例。所有的分页查询都是通用的
	 */
	protected final Pageable buildPageableInstance(PageRequest query) throws Exception{
		{
			if(query == null){
				throw new ParamterNullException("query", PageRequest.class);
			}
		}
		
		//构建Pageable实例，它的pageIndex是从0开始的。
		int pageIndex,pageSize;
		if (query.getPage() <= 0) {
			pageIndex = 0;
		}
		else{
			pageIndex = query.getPage() - 1;
		}
		if(query.getRows() <= 0){
			pageSize = 20;
		}
		else{
			pageSize = query.getRows();
		}
		Direction direction;
		if(query.getOrderDirection().equalsIgnoreCase("desc")){
			direction = Direction.DESC;
		}
		else{
			direction = Direction.ASC;
		}
		Order order = new Order(direction, query.getSortField());
		Sort sort = new Sort(order);
		Pageable pageable = new org.springframework.data.domain.PageRequest(pageIndex, pageSize, sort);
		
		return pageable;
	}
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 构建 根据父节点查询 的Specification实现
	 * 
	 * @param <T> 具体实体类的类型
	 */
	protected final class TreeSpecification<T> implements Specification<T>{
		
		private String joinAttributeName;
		private String searchAttributeName;
		private Integer searchAttributeValue;
		
		public String getJoinAttributeName() {
			return joinAttributeName;
		}
		public void setJoinAttributeName(String joinAttributeName) {
			this.joinAttributeName = joinAttributeName;
		}
		public String getSearchAttributeName() {
			return searchAttributeName;
		}
		public void setSearchAttributeName(String searchAttributeName) {
			this.searchAttributeName = searchAttributeName;
		}
		public Integer getSearchAttributeValue() {
			return searchAttributeValue;
		}
		public void setSearchAttributeValue(Integer searchAttributeValue) {
			this.searchAttributeValue = searchAttributeValue;
		}
		
		/**
		 * @param joinAttributeName    	父节点属性，不能为空
		 * @param searchAttributeName	父节点属性的主键属性名称，一般继承自IdEntity，默认为id
		 * @param searchAttributeValue	父节点属性的主键值，不能为空
		 */
		public TreeSpecification(String joinAttributeName, String searchAttributeName, Integer searchAttributeValue) {
			super();
			this.joinAttributeName = joinAttributeName;
			this.searchAttributeName = searchAttributeName;
			this.searchAttributeValue = searchAttributeValue;
		}
		
		
		@Override
		public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			
			if (StringUtils.isEmpty(joinAttributeName)) {
				if (logger.isErrorEnabled()) {
					logger.error("父节点属性 joinAttributeName 不能为空");
				}
				return null;
			}
			if (StringUtils.isEmpty(this.searchAttributeValue)) {
				if(logger.isErrorEnabled()){
					logger.error("强制规定要查询的父节点的值不能为空");
				}
				return null;
			}
			Join<T, T> parentJoin = root.join(this.joinAttributeName, JoinType.INNER);//inner join
			String attrName = StringUtils.isEmpty(this.searchAttributeName) ? "id" : this.searchAttributeName;
			
			Path<String> parentIdPath = parentJoin.get(attrName);//where id = ?
			return cb.equal(parentIdPath, this.searchAttributeValue);
		}
	}
}
