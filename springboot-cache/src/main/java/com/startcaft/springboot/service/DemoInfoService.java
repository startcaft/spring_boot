package com.startcaft.springboot.service;

import com.startcaft.springboot.entity.DemoInfo;

public interface DemoInfoService {
	
	void delete(Integer id) throws Exception;
	
	DemoInfo update(DemoInfo demoInfo) throws Exception;
	
	DemoInfo findById(Integer id) throws Exception;
	
	DemoInfo save(DemoInfo demoInfo);
}
