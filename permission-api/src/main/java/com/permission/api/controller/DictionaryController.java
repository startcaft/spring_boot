package com.permission.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.permission.core.vo.DictionaryTypeVo;
import com.permission.service.DictionaryTypeService;

@RestController
@RequestMapping("/api/admin/dic")
public class DictionaryController extends BaseController {
	
	@Autowired
	private DictionaryTypeService dicTypeService;
	
	@RequestMapping(value="/add",method=RequestMethod.POST,consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public Msg addDicType(DictionaryTypeVo vo){
		{
			Msg msg = new Msg();
			try {
				dicTypeService.insertRecord(vo);
				msg.setSuccess(true);
				msg.setData(vo);
			} catch (Exception e) {
				msg.setError(e.getMessage());
			}
			return msg;
		}
	}
}
