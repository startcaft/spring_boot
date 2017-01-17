package com.startcaft.mvc.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.startcaft.mvc.config.entity.DemoObj;

@Controller
public class ConverterController {
	
	@RequestMapping(value="/convert",produces={"application/x-startcaft"})
	public DemoObj convert(@RequestBody DemoObj obj){
		return obj;
	}
}
