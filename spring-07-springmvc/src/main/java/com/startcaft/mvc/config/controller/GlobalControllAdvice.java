package com.startcaft.mvc.config.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllAdvice {
	
	//统一的异常处理
	@ExceptionHandler(value=Exception.class)
	public ModelAndView exception(Exception exception,WebRequest request){
		ModelAndView mv = new ModelAndView("error");//error页面
		mv.addObject("errorMessage",exception.getMessage());
		return mv;
	}
	
	//将键值数据添加到全局
	@ModelAttribute
	public void addGlobalAttibutes(Model model){
		model.addAttribute("msg", "额外信息");
	}
	
	//定制WebDataBinder，更多请参考WebDataBinder API
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.setDisallowedFields("id");//此处演示忽略 request 参数的id
	}
}
