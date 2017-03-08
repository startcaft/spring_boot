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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

/**
 * 构建树状结构的基础Service，
 * 提供一个内部类，用于构造一个Specification接口，从而来过滤指定的父节点的数据，而后用于递归生成NodeTree对象。
 */
public abstract class BaseTreeService extends BaseService {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseTreeService.class);
	
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
			Join<T, T> parentJoin = root.join(this.joinAttributeName, JoinType.INNER);//inner join 父节点的类型属性
			String attrName = StringUtils.isEmpty(this.searchAttributeName) ? "id" : this.searchAttributeName;
			
			Path<String> parentIdPath = parentJoin.get(attrName);//where id = ?
			return cb.equal(parentIdPath, this.searchAttributeValue);
		}
	}
}
