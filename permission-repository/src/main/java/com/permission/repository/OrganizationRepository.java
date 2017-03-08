package com.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.permission.core.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Integer>
												,JpaSpecificationExecutor<Organization>{
	
	@Query("select org from Organization where org.name = ?")//name是唯一的
	Organization queryByName(String orgName);
}
