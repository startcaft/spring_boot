package com.startcaft.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.startcaft.springboot.entity.DemoInfo;
import com.startcaft.springboot.service.DemoInfoService;

@RestController
public class DemoInfoController {
	
	@Autowired
	private DemoInfoService service;
	
	@RequestMapping("/test")
	public String test() throws Exception{
		
		//存入数据，缓存中有key为 demoInfo_#id 的缓存。
		DemoInfo demoInfo = new DemoInfo();
        demoInfo.setName("张三");
        demoInfo.setPwd("123456");
        DemoInfo demoInfo2 = service.save(demoInfo);
        
        //查询数据，不走缓存，save之后的key为 demoInfo。
        System.out.println(service.findById(demoInfo2.getId()));
        //查询数据，走缓存，findById之后，缓存有个可以 为demoInfo_'+#id 的缓存元素
        System.out.println(service.findById(demoInfo2.getId()));
        return "ok";
	}
}
