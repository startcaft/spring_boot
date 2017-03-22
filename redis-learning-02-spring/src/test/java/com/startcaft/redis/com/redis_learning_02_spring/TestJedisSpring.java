package com.startcaft.redis.com.redis_learning_02_spring;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.redis.javabeans.SerializingUtil;
import com.redis.javabeans.UserInfo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

public class TestJedisSpring {
	
	private static JedisPool pool = null;
	private static ApplicationContext context = null;
	
	static{
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		pool = (JedisPool) context.getBean("jedisPool");
	}
	
	//其他操作，对key的操作，对db的操作
	@Test
	public void testOther() throws InterruptedException{
		{
			Jedis jedis = pool.getResource();
			
			//对key的模糊查询
			Set<String> keys = jedis.keys("user.userid.*");
			Assert.assertEquals(0, keys.size());
			
			//是否包含指定的key
			Boolean isExists = jedis.exists("user.userid.1001");
			Assert.assertFalse(isExists);
			
			//设置失效时间，expire：时间单位秒
			jedis.setex("user.userid.10101", 30, "startcaft");
			//获取存活时间:time to live
			System.out.println(jedis.ttl("user.userid.10101"));
			//去掉失效时间
			jedis.persist("user.userid.10101");
			
			//自增的类型
			jedis.set("amount", 100+"");
			//递增或递减：incr()/decr()
			jedis.incr("amount");
			//增加或减少：incrBy()/decrBy()
			jedis.incrBy("amount", 20);
			
			
			//事务支持
			Transaction tx = jedis.multi();
			for(int i = 0;i < 10;i ++) {  
			     tx.set("key" + i, "value" + i);   
			     System.out.println("--------key" + i);  
			     Thread.sleep(1000);    
			}  
			//执行职务，针对每一个操作，返回其执行的结果，成功即为ok
			List<Object> results = tx.exec();
			System.out.println(results);
			
			//删除key
			jedis.del("user.userid.10101");
			jedis.del("amount");
			
			//清空当前db
			jedis.flushDB();
			//清空所有db
			jedis.flushAll();
					
			jedis.close();
		}
	}
	
	//存放HashMap数据
	@Test
	public void testHash(){
		{
			Jedis jedis = pool.getResource();
			jedis.del("capital");
			
			Map<String, String> capital = new HashMap<String,String>();
			capital.put("湖北", "武汉");		
			
			capital.put("湖南", "长沙");
			
			jedis.hmset("capital", capital);
			
			//获取数据
			List<String> cities = jedis.hmget("capital", "湖北","湖南");
			System.out.println(cities);
			
			jedis.del("capital");
			jedis.close();
		}
	}
	
	
	//在Set基础上增加了排序
	@Test
	public void testSoetSet(){
		{
			Jedis jedis = pool.getResource();
			
			jedis.del("user");
			
			jedis.zadd("user", 22,"James");
			jedis.zadd("user", 24,"James");//元素相同时，更新为当前的权重
			
			Set<String> user = jedis.zrange("user", 0, -1);
			
			Assert.assertEquals(user.size(), 1);
			
			jedis.del("user");
			
			jedis.close();
		}
	}
	
	//无序集合Set常用操作，没有重复元素
	@Test
	public void testSet(){
		{
			Jedis jedis = pool.getResource();
			
			jedis.sadd("fruit", "apple");
			jedis.sadd("fruit", "pear","watermelon");
			jedis.sadd("fruit", "apple");
			
			//遍历集合
			Set<String> fruit = jedis.smembers("fruit");
			Iterator<String> iter = fruit.iterator();
			while(iter.hasNext()){
				String next = iter.next();
				System.out.println(next);
			}
			
			//移除元素
			jedis.srem("fruit", "pear");
			
			//返回长度
			System.out.println("set length:" + jedis.scard("fruit"));
			
			//判断是否包含
			Assert.assertTrue(jedis.sismember("fruit", "apple"));
			
			/*
			 * 集合操作：包括交集运算，差集运算，并集运算
			 */
			jedis.sadd("food", "bread", "milk");  
			Set<String> fruitFood = jedis.sunion("fruit", "food");
			iter = fruitFood.iterator();
			while(iter.hasNext()){
				String next = iter.next();
				System.out.println(next);
			}
			
			jedis.del("fruit");
			
			jedis.close();
		}
	}
	
	//有序集合List常用操作，可以有重复元素
	@Test
	public void testList(){
		{
			Jedis jedis = pool.getResource();
			
			//右边入队(从底部开始算)，左边入队(从头部开始算)
			jedis.rpush("userList", "张三","李四","王五");
			
			//删除并获取第一个元素，这得根据左还是右来判断
			//System.out.println("左边出对，右边出栈（pop）--->" + jedis.lpop("userList"));
			Assert.assertEquals("张三", jedis.lpop("userList"));//李四，王五
			
			//返回列表的指定范围，从0开始，到最后一个(-1)[包含]，Redis的TopN操作。
			List<String> userList = jedis.lrange("userList", 0, -1);//李四，王五
			Assert.assertNotNull(userList);
			Assert.assertEquals(userList.size(), 2);
			
			//设置位置1处为新值，替换掉原有的值
			jedis.lset("userList", 1, "startcaft");
			userList = jedis.lrange("userList", 0, -1);//李四，startcaft
			for (String string : userList) {
				System.out.println(string);
			}
			
			//长度
			System.out.println("userList length:" + jedis.llen("userList"));
			
			//进行剪裁操作[包含]
			System.out.println("userList trim from 1 to 2:" + jedis.ltrim("userList", 1, 1));
			
			//删除指定key
			Long del = jedis.del("userList");
			System.out.println(del);
			
			jedis.close();
		}
	}
	
	
	/*
	 * 序列化与反序列化
	 */
	@Test
	public void test(){
		Jedis jedis = pool.getResource();
		
		UserInfo user = new UserInfo();
		user.setId(1001);
		user.setName("startcaft");
		user.setAge(100);
		
		//序列化
		String key = "user.userid." + user.getId();
		jedis.set(key.getBytes(), SerializingUtil.serialize(user));
		
		//反序列化
		UserInfo newUser = (UserInfo) SerializingUtil.deserialize(jedis.get(key.getBytes()));
		
		Assert.assertEquals(user, newUser);//重写hashCode和equals方法，id相同即可
		
		System.out.println(newUser);
		
		//删除指定key
		Long del = jedis.del(key);
		System.out.println(del);
		
		jedis.close();
	}
	
	@After
	public void clean(){
		pool.close();
	}
}
