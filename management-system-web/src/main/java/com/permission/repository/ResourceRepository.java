package com.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.permission.core.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Integer>,
											JpaSpecificationExecutor<Resource>{

}
