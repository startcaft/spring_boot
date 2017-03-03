package com.management.app.listsner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

public class JpaCacheListsner implements CacheEventListener {
	
	private static final Logger logger = LoggerFactory.getLogger(JpaCacheListsner.class);
	
	@Override
	public void dispose() {
	}

	@Override
	public void notifyElementEvicted(Ehcache arg0, Element arg1) {
	}

	@Override
	public void notifyElementExpired(Ehcache arg0, Element arg1) {
	}

	/*
	 * 监听 cache put 事件
	 */
	@Override
	public void notifyElementPut(Ehcache cache, Element element) throws CacheException {
		if (logger.isInfoEnabled()) {
			logger.info("cache name:" + cache.getName() + "--- element key:" + element.getObjectKey());
		}
	}

	@Override
	public void notifyElementRemoved(Ehcache arg0, Element arg1) throws CacheException {
	}

	@Override
	public void notifyElementUpdated(Ehcache arg0, Element arg1) throws CacheException {
	}

	@Override
	public void notifyRemoveAll(Ehcache arg0) {
	}

	public Object clone() throws CloneNotSupportedException {
	      throw new CloneNotSupportedException();
	}
}
