package com.startcaft.www.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.startcaft.www.entity.Demo;
import com.startcaft.www.service.TestService;

@RestController
public class TestController {

	@Resource
	private TestService testService;

	@RequestMapping("/test1")
	public String test() {
		for (Demo d : testService.getList()) {
			System.out.println(d);
		}
		System.out.println("-------------------------------------------------");
		for (Demo d : testService.getListByDs1()) {
			System.out.println(d);
		}
		return "ok";
	}
}
