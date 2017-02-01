package com.startcaft.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.startcaft.www.model.StartcaftSettings;
import com.startcaft.www.model.StartcaftSettings2;

@RestController
public class TestController {
	
	@Autowired
	StartcaftSettings s;
	
	@Autowired
	StartcaftSettings2 s2;
	
	@RequestMapping("/test")  
	public String test(){
		
		System.out.println(s.getUserid()+"---"+s.getUsername()); 
		System.out.println(s2.getUserid()+"---"+s2.getUsername()); 
		return "ok";
	}
}
