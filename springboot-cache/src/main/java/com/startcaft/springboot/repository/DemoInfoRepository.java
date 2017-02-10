package com.startcaft.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import com.startcaft.springboot.entity.DemoInfo;

public interface DemoInfoRepository extends CrudRepository<DemoInfo, Integer> {

}
