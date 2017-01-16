package com.startcaft.thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskService {
	
	//该注解如何在类级别，则表明该类中的所有方法都是异步方法
	@Async
	public void executeAsyncTask(Integer i){
		System.out.println("执行异步任务: " + i);
	}
	
	@Async
	public void executeAsyncTaskPlus(Integer i){
		System.out.println("执行异步任务+1: " + (i + 1));
	}
}
