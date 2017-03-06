package com.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.permission.core.entity.DictionaryType;

public interface DictionaryTypeRepository extends JpaRepository<DictionaryType, Integer>,
												JpaSpecificationExecutor<DictionaryType>{
	
	@Query("select dt from DictionaryType dt where dt.name = ?")
	DictionaryType queryByName(String name);
}
