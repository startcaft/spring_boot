package com.startcaft.www.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@RequestMapping("/hello")
    public String hello(String name,int state){
       return"name "+name+"---"+state;
    }
}
