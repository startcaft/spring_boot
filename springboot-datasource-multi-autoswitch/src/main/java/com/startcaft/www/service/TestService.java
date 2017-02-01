package com.startcaft.www.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.startcaft.www.config.TargetDataSource;
import com.startcaft.www.dao.TestDao;
import com.startcaft.www.entity.Demo;

@Service
public class TestService {
	
	@Resource
    private TestDao testDao;
	
	/**
     * 不指定数据源使用默认数据源
     */
    public List<Demo> getList(){
       return testDao.getList();
    }
    
    /**
     * 指定数据源
     * @return
     */
    @TargetDataSource("ds1")
    public List<Demo> getListByDs1(){
        return testDao.getListByDs1();
    }
}
