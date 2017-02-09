package com.startcaft.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.startcaft.www.dao.TopicRepository;
import com.startcaft.www.entity.Topic;

@Service
public class TopicService {
	
	@Autowired
	private TopicRepository topicRepo;
	
	@Transactional
	public void save(Topic entity){
		topicRepo.save(entity);
	}
	
	public List<Topic> getByPid(Integer pId){
		return topicRepo.getByPid(pId);
	}
	
	public void tree(Integer id,Integer level){
		
		StringBuffer strPre = new StringBuffer("");
		for (int i = 0; i < level; i++) {
			strPre.append("\t");
		}
		
		List<Topic> childs = this.getByPid(id);
		if (childs != null && childs.size() > 0) {
			for (Topic topic : childs) {
//				System.out.println(strPre.append(topic.getContent()));
				System.out.println(strPre + topic.getContent());
				//递归
				tree(topic.getId(), level + 1);
			}
		}
//		strPre = new StringBuffer("");
	}
}
