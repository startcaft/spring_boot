package com.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.permission.core.entity.DictionaryType;

public interface DictionaryTypeRepository extends JpaRepository<DictionaryType, Integer>,
												JpaSpecificationExecutor<DictionaryType>{

}
