package com.startcaft.www.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.startcaft.www.entity.Topic;
import com.startcaft.www.service.TopicService;

@RestController
@RequestMapping("/topic")
public class TopicController {
	
	@Autowired
	private TopicService service;
	
	@RequestMapping("/tree")
	public void showTree(){
		List<Topic> childs = service.getByPid(0);//获取顶层节点的所有子节点
		if (childs != null && childs.size() > 0) {
			for (Topic t : childs) {
				System.out.println(t.getContent());
				service.tree(t.getId(), 1);		//顶层节点的子节点的level定义为1
			}
		}
	}
	
	@RequestMapping("/save")
	public String save(){
		Topic t = new Topic();
		t.setTitle("大象被打了!");
		t.setContent("大象被打了");
		t.setPid(0);
		t.setCreateTime(new Date());
		
		service.save(t);
		return "com.startcaft.www.controller.save";
	}
}
