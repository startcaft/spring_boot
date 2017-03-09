package com.permission.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.permission.core.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>,
										JpaSpecificationExecutor<Role>{
	
	@Query("select r from Role r where r.name = ?")
	Role queryByName(String roleName);
	
	List<Role> findByIdIn(List<Integer> ids);
}
