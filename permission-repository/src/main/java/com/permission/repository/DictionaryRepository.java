package com.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.permission.core.entity.Dictionary;

public interface DictionaryRepository extends JpaRepository<Dictionary, Integer>,
											JpaSpecificationExecutor<Dictionary>{

}
