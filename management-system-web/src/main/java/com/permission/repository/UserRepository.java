package com.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.permission.core.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>,
										JpaSpecificationExecutor<User>{

}