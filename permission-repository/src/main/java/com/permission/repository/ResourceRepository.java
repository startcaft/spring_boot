package com.permission.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.permission.core.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Integer>,
											JpaSpecificationExecutor<Resource>{
	
	@Query("select r from Resource r join fetch r.parentResource where r.id =?")
	Resource queryById(Integer resourceId);
	

	@Query("select distinct r from Resource r "
			+ "join fetch r.parentResource parent "
			+ "join fetch r.roles ro "
			+ "join ro.users u "
			+ "where parent.id = :pid and r.resourcetype = :type and u.id = :userId order by r.seq")
	List<Resource> queryByParentAndUser(@Param("userId") Integer userId,
								@Param("type") Integer resourcetype,
								@Param("pid") Integer pid);
	
	/**
	 * 通过解析方法名创建查询(注意：符合Spring Data Jpa规范的方法命名)
	 */
	List<Resource> findByIdIn(List<Integer> ids);
}
