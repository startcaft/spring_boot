package com.startcaft.www.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

/*
 * 为什么无法看到session的过程：
 * http://zhidao.baidu.com/link?url=EP-wlLvKpo8zI5NaIZrESzCdivq3Xg8VgOWQOvfpSLl3opTgvESerpo4wsG6tOs_dm6cQQMF_kQ6THNjNzr2Nq
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionIdListener, HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("Session 被创建");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
	}

	@Override
	public void sessionIdChanged(HttpSessionEvent se, String oldSessionId) {

	}
}
