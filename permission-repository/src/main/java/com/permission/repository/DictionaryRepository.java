package com.permission.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.permission.core.entity.Dictionary;

public interface DictionaryRepository extends JpaRepository<Dictionary, Integer>,
											JpaSpecificationExecutor<Dictionary>{
	
	//@Query("SELECT dic FROM Dictionary dic WHERE p.dictionaryType.id = ?")//从Many到One，产生两条SQL语句
	@Query("select dic from Dictionary dic join  FETCH dic.dictionaryType dicType where dicType.id = ?")
	List<Dictionary> queryByDicTypeId(Integer typeId);
	
	@Query("select dic from Dictionary dic join  FETCH dic.dictionaryType where dic.id = ?")
	Dictionary queryById(Integer id);
}
