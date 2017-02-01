package com.startcaft.www.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源路由类：
 * 		spring 底层提供了 AbstractRoutingDataSource 类进行数据源的路由，
 * 		我们主要继承这个累，实现里面的方法即可，主要实现方法：determineCurrentLookupKey()，
 * 		该方法只需要返回一个数据库的名称即可，所以我们核心的是有一个类来管理数据源的线程池，
 * 		另外就是使用 aop 技术在执行事务之前进行数据源的切换。
 * @author Administrator
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		
		/*
		 * DynamicDataSourceContextHolder代码中使用setDataSourceType
		 * 设置当前的数据源，在路由类中使用getDataSourceType进行获取,
		 * 交给AbstractRoutingDataSource进行注入使用。
		 */
		return DynamicDataSourceContextHolder.getDataSourceType();
	}

}
