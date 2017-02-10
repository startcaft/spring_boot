package com.startcaft.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.startcaft.springboot.entity.DemoInfo;
import com.startcaft.springboot.repository.DemoInfoRepository;
import com.startcaft.springboot.service.DemoInfoService;

import javassist.NotFoundException;

@Service
public class DemoInfoServiceImpl implements DemoInfoService {
	
	//这里的单引号不能少，否则会报错，被识别为一个对象
	public static final String CACHE_KEY = "'demoInfo'";
	//Cache注解中的value属性，指定 cache 配置文件中的 cache
	public static final String DEMO_CACHE_NAME = "demo";
	
	@Autowired
	private DemoInfoRepository demoRepo;
	
	/*
	 * 删除数据
	 */
	@CacheEvict(value=DEMO_CACHE_NAME,key="'demoInfo_'+#id")//清除缓存
	@Override
	public void delete(Integer id) throws Exception {
		demoRepo.delete(id);
	}

	/*
	 * 修改数据
	 * 在只是 Spring Cache 的环境下，对于使用@Cacheable标注的方法，Spring在每次执行前都会检查缓存中是否存在相同key的缓存元素。
	 * 如果存在就不再执行该方法，而是直接从缓存中获取进行返回，否则才会执行并返回结果存入指定的缓存中。
	 * 
	 * @CachePut也可以声明一个方法支持缓存功能。
	 * 与@Cacheable不同的是，使用@CachePut标注的方法在执行前不会去检查缓存，而是每次都会执行该方法，并将结果存入缓存中。
	 * 
	 * @CachePut也可以标注在类上和方法是那个。
	 */
	@CachePut(value = DEMO_CACHE_NAME,key = "'demoInfo_'+#updated.getId()")
	//@CacheEvict(value = DEMO_CACHE_NAME,key = "'demoInfo_'+#updated.getId()")//这是清除缓存.
	@Override
	public DemoInfo update(DemoInfo demoInfo) throws Exception {
		{
			DemoInfo model = demoRepo.findOne(demoInfo.getId());
			if (model == null) {
				throw new NotFoundException("Not Find");
			}
			
			model.setName(demoInfo.getName());
			model.setPwd(demoInfo.getPwd());
			return model;
		}
	}
	
	/*
	 * 查询数据
	 */
	@Cacheable(value=DEMO_CACHE_NAME,key="'demoInfo_'+#id")
	@Override
	public DemoInfo findById(Integer id) throws Exception {
		{
			System.err.println("没有走缓存！" + id);
		}
		{
			return demoRepo.findOne(id);
		}
	}
	
	/*
	 * 保存
	 */
	@CacheEvict(value=DEMO_CACHE_NAME,key=CACHE_KEY)
	@Override
	public DemoInfo save(DemoInfo demoInfo) {
		{
			return demoRepo.save(demoInfo);
		}
	}

}
