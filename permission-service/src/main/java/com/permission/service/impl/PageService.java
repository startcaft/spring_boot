package com.permission.service.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import com.permission.core.exception.ParameterNullException;
import com.permission.core.queryable.PageRequest;

/**
 * 定义一个通用的构建Pageable实例的方法(Jpa分页时用到)。
 */
public abstract class PageService extends TreeService {
	
	/**
	 * 构建Pageable实例。所有的分页查询都是通用的
	 */
	protected final Pageable buildPageableInstance(PageRequest query) throws Exception{
		{
			if(query == null){
				throw new ParameterNullException("query", PageRequest.class);
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
}
