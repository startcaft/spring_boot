package com.swagger.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;


@Api(value="系统用户相关api")
@RestController
@RequestMapping(value="/users")
public class UserController {
	
	@ApiOperation(value="获取用户详细信息",notes="根据url路径中的id参数来获取用户详细信息")
	@ApiImplicitParam(name="userid",value="用户ID",required=true,dataType="Integer")
	@RequestMapping(value="/{userid}",method=RequestMethod.GET)
	public UserBean hello(){
		{
			UserBean userBean = new UserBean();
			userBean.setUsername("startcaft");
			userBean.setPassword("123456");
			
			return userBean;
		}
	}
}
