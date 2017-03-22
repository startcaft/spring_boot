package com.startcaft.redis.com.redis_learning_03_springdataredis;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.redis.dao.UserStorage;
import com.redis.javabean.UserInfo;


public class SDRTest {
	
	private static ApplicationContext context = null;
	private static UserStorage dao = null;
	
	static{
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		dao = (UserStorage) context.getBean("userDao");
	}
	
	
	@Test
	public void testSaveOrUpdate(){
		{
			UserInfo user = new UserInfo();
			user.setUserid(1001);
			user.setUsername("startcaft");
			user.setUsermail("startcaft@qq.com");
			
			dao.addOrUpdate(user);
		}
	}
	
	@Test
	public void testLoad(){
		System.out.println(dao.load(1001));
	}
	
	@Test
	public void delete(){
		dao.delete(1001);
		UserInfo user = dao.load(1001);
		
		Assert.assertNull(user);
	}
}
