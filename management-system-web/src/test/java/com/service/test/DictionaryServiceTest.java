package com.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.management.app.App;
import com.permission.core.exception.ParamterNullException;
import com.permission.core.queryable.DictionaryQuery;
import com.permission.core.queryable.PageInfo;
import com.permission.core.vo.DictionaryTypeVo;
import com.permission.core.vo.DictionaryVo;
import com.permission.core.vo.NodeTree;
import com.permission.service.DictionaryItemService;
import com.permission.service.DictionaryTypeService;


@SpringBootTest(classes=App.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DictionaryServiceTest {
	
	@Autowired
	private DictionaryTypeService service;
	
	@Autowired
	private DictionaryItemService itemService;
	
	@Test(expected=ParamterNullException.class)
	public void testInsertRecordException() throws Exception{
		
		DictionaryTypeVo vo = new DictionaryTypeVo();
		service.insertRecord(vo);
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testInsertRecord() throws Exception{
		
		DictionaryTypeVo vo = new DictionaryTypeVo();
		vo.setName("居民基本信息");
		vo.setCode("001");
		vo.setSeq(1);
		vo.setDescription("人口基本信息包含字典");
		service.insertRecord(vo);
	}
	
	@Test
	public void testRecursiveTree() throws Exception{
		
		NodeTree node = new NodeTree();
		node.setId(13);
		node.setName("人口基本信息包含字典");
		
		service.recursiveTree(node);
		
		System.out.println(node);
		
		///////////////////////////////////////////////////////
		
		NodeTree node1 = new NodeTree();
		node1.setId(13);
		node1.setName("人口基本信息包含字典");
		
		service.recursiveTree(node1);
		System.out.println(node1);
	}
	
	@Test
	public void testPageQuery() throws Exception{
		
		DictionaryQuery query = new DictionaryQuery();
		query.setDicName("xxx");
		
		PageInfo<DictionaryVo> page = itemService.pageQuery(query);
		System.out.println(page);
	}
}
