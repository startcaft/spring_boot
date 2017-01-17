package com.startcaft.mvc.config.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.startcaft.mvc.config.entity.DemoObj;

@RestController
@RequestMapping("/rest")
public class DemoRestController {
	
	@RequestMapping(value="/getjson",produces={"application/json;charset=UTF-8"})
	public DemoObj getJson(){
		return new DemoObj(1l, "yy");
	}
	
	@RequestMapping(value="/getxml",produces={"application/xml;charset=UTF-8"})
	public DemoObj getXml(){
		return new DemoObj(1l, "yy");
	}
}
