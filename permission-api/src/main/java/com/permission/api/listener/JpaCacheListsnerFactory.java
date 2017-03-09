package com.permission.api.listener;

import java.util.Properties;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

/**
 * Ehcache缓存监听器工厂类，用于创建并返回 CacheEventListener 的实现类，
 * ehcache.xml文件，在指定<cache>节点下配置<cacheEventListenerFactory class="工厂全类名">
 */
public class JpaCacheListsnerFactory extends CacheEventListenerFactory {

	@Override
	public CacheEventListener createCacheEventListener(Properties properties) {
		{
			return new JpaCacheListsner();
		}
	}

}
