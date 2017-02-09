package com.startcaft.www.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.startcaft.www.entity.Topic;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
	
	@Query(value="select * from tb_topic t where t.pid = ?",nativeQuery=true)
	public List<Topic> getByPid(Integer id);
}
