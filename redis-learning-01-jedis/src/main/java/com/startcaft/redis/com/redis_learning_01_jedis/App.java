package com.startcaft.redis.com.redis_learning_01_jedis;

import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/*
 * 使用Jedis提供的Java API对Redis进行操作，是Redis官方推崇的方式；
 * 并且，Jedis提供的对Redis的支持也最为灵活，全面；
 * 不足之处，就是编码复杂度较高；
 */
public class App {
	public static void main(String[] args) {
		simpleJedis();
		System.out.println("############################################");
		jedisPool();
	}
	
	/*
	 * 直接创建Jedis连接对象
	 */
	public static void simpleJedis(){
		{
			//获取连接，注意点：1，连接的Redis所在的服务器的防火墙。2，Redis网络配置中的bind模式
			Jedis jedis = new Jedis("192.168.1.222", 8223);
			jedis.set("k1", "hello redis");
			String k1 = jedis.get("k1");
			
			System.out.println("===>key k1=" + k1);
			
			jedis.del("k1");
			System.out.println("===>delete key:k1 done");
			
			//关闭连接
			jedis.close();
		}
	}
	
	public static void jedisPool(){
		{
			ResourceBundle bundle = ResourceBundle.getBundle("redis");
			//连接池配置对象
			JedisPoolConfig config = new JedisPoolConfig();
			config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
			config.setMaxTotal(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
			config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
			config.setMinIdle(Integer.valueOf(bundle.getString("redis.pool.minIdle")));
			
			String host = bundle.getString("redis.host");
			Integer port = Integer.valueOf(bundle.getString("redis.port"));
			Integer timeout = Integer.valueOf(bundle.getString("redis.timeout"));
			//连接池
			JedisPool pool = new JedisPool(config, host, port, timeout, null);
			
			//通过连接池对象获取Jedis对象
			Jedis jedis = pool.getResource();
			
			jedis.set("k1", "hello jedispool");
			String k1 = jedis.get("k1");
			
			System.out.println("===>key k1=" + k1);
			
			jedis.del("k1");
			System.out.println("===>delete key:k1 done");
			
			//用完之后将Jedis对象归还给连接池，废弃了，官方建议直接close
			//pool.returnBrokenResource(jedis);
			jedis.close();
			pool.close();
		}
	}
}
