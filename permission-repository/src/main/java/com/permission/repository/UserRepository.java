package com.permission.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.permission.core.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>,
										JpaSpecificationExecutor<User>{

	@Query("select u from User u left join fetch u.organization org where org.id = ?")
	List<User> queryByOrgId(Integer orgId);
}
