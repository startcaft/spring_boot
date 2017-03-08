package com.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.permission.core.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Integer>,
											JpaSpecificationExecutor<Resource>{
	
	@Query("select r from Resource r join fetch r.parentResource where r.id =?")
	Resource queryById(Integer resourceId);
}
