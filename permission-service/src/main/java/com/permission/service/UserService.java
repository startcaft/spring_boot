package com.permission.service;

import com.permission.core.vo.UserVo;

public interface UserService {

	void add(UserVo vo) throws Exception;

	void delete(Integer id) throws Exception;

	void edit(UserVo vo) throws Exception;

	UserVo getDetail(Integer id) throws Exception;
	
	UserVo login(UserVo user) throws Exception;
	
	boolean editUserPwd(String oldPwd, String pwd,Integer userId);
}
