package com.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.permission.core.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Integer>
												,JpaSpecificationExecutor<Organization>{
	
	@Query("select org from Organization org where org.name = ?")//name是唯一的
	Organization queryByName(String orgName);
	
	@Query("select org from Organization org join  FETCH org.parentOrg where org.id = ?")
	Organization queryById(Integer id);
	
	
	/**
	 * 查询父节点为空的组织部门
	 */
	//@Query("select count(o) from Organization o.parentOrg is null")
	Integer findByParentOrgIsNull();
}
