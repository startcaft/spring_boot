package com.startcaft.www.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.startcaft.www.demo.Shanhy;

@RestController
public class MyController {

	@Resource(name = "shanyA")
	private Shanhy shanhyA;

	@Autowired
	@Qualifier("shanyB")
	private Shanhy shanhyB;
	
	@RequestMapping("/test")
    public String test(){
       shanhyA.dispaly();
       shanhyB.dispaly();
       return"test";
    }
}
