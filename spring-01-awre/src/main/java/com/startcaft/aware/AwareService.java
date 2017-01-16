package com.startcaft.aware;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class AwareService implements BeanNameAware,ResourceLoaderAware {
	
	private String beanName;
	private ResourceLoader loader;
	
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.loader = resourceLoader;
	}

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}
	
	public void outputResut(){
		System.out.println("Bean的名称为:" + this.beanName);
		
		Resource resource = loader.getResource("classpath:com/startcaft/aware/test.txt");
		try {
			System.out.println("ResourceLoader 加载的文件内容为: " + IOUtils.toString(resource.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
