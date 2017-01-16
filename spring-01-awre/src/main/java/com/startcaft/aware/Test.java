package com.startcaft.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
	
public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);
		
		AwareService awareService = context.getBean(AwareService.class);
		awareService.outputResut();
		
		context.close();
	}
}
