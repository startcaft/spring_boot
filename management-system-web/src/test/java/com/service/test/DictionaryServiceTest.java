package com.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.management.app.App;
import com.permission.service.DictionaryService;

@SpringBootTest(classes=App.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DictionaryServiceTest {
	
	@Autowired
	private DictionaryService service;
	
	@Test
	public void testCheckNameExists() throws Exception{
		
		String dicType = null;
		boolean result = service.checkNameExists(dicType);
		Assert.assertTrue(result);
	}
}
