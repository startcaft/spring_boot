package com.redis.dao;

import org.springframework.data.redis.core.RedisTemplate;

import com.redis.javabean.UserInfo;

/**
 * SDR入门操作
 */
public class UserStorage {
	
	private RedisTemplate<String,UserInfo> redisTemplate;

	public RedisTemplate<String, UserInfo> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, UserInfo> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	public void addOrUpdate(UserInfo user){
		redisTemplate.opsForValue().set("user.userid." + user.getUserid(), user);
	}
	
	public UserInfo load(Integer userId){
		return redisTemplate.opsForValue().get("user.userid." + userId);
	}
	
	public void delete(Integer userId){
		redisTemplate.delete("user.userid." + userId);
	}
}
