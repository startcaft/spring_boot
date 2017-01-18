package com.startcaft.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.startcaft.springboot.entity.Book;
import com.startcaft.springboot.entity.User;

@RestController
@RequestMapping("/springboot")
public class HelloController {
	
	@Autowired
	private User user;
	
	@Autowired
	private Book book;
	
	@RequestMapping(value="/{name}",method=RequestMethod.GET)
	public String sayWorld(@PathVariable("name") String name){
		System.out.println("book.author:" + user.getName());
		return "Hello " + name;
	}
	
	@RequestMapping(value="/book",method=RequestMethod.GET)
	public String getBook(){
		return book.toString();
	}
}
