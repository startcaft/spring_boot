package com.repository.test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.management.app.App;
import com.permission.core.entity.DictionaryType;
import com.permission.repository.DictionaryTypeRepository;


@SpringBootTest(classes=App.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DictionaryTypeRepositoryTest {

	@Autowired
	private DictionaryTypeRepository dicTypeRepo;
	
	@Transactional
	@Rollback(true)
	@Test
	public void test(){
		
		List<DictionaryType> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			DictionaryType model = new DictionaryType();
			model.setName("dic_type_" + i);
			model.setCode("dic_code_" + i);
			model.setParentType(null);
			model.setSeq(i + 1);
			
			list.add(model);
		}
		
		dicTypeRepo.save(list);
	}
}
